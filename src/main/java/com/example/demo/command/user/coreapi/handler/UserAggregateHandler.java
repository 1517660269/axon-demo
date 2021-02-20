package com.example.demo.command.user.coreapi.handler;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.command.user.aggregate.UserAggregate;
import com.example.demo.command.user.coreapi.command.ChangeUserProfileCommand;
import com.example.demo.command.user.coreapi.command.RegisterUserCommand;
import com.example.demo.command.user.coreapi.event.UserProfileChangedEvent;
import com.example.demo.command.user.coreapi.event.UserRegisteredEvent;
import com.example.demo.exception.EntityExistsException;
import com.example.demo.exception.InvalidVerificationCodeException;
import com.example.demo.query.user.repository.UserRepository;

@Component
public class UserAggregateHandler {

	@Autowired
	private Repository<UserAggregate> repository;

	@Autowired
	private UserRepository userRepository;

	@CommandHandler
	public void handle(RegisterUserCommand command) throws Exception {
		if (this.userRepository.findByMobile(command.getMobile()) != null)
			throw new EntityExistsException("该手机号已经注册");

		if (this.userRepository.findByUsername(command.getUsername()) != null)
			throw new EntityExistsException("用户名已经注册");

		if (!"0000".equals(command.getVerificationCode()))
			throw new InvalidVerificationCodeException("手机验证码错误");

		this.repository.newInstance(() -> {
			AggregateLifecycle.apply(new UserRegisteredEvent(command.getUserId(), command.getMobile(), command.getUsername(), command.getPassword(), command.getVerificationCode()));
			return new UserAggregate();
		});
	}

	@CommandHandler
	public void handle(ChangeUserProfileCommand command) {
		Aggregate<UserAggregate> aggregate = this.repository.load(command.getId());
		String username = aggregate.invoke(UserAggregate::getUsername);

		if (!username.equals(command.getUsername())) {
			if (this.userRepository.findByUsername(command.getUsername()) != null)
				throw new EntityExistsException("用户名已经注册");
		}

		aggregate.execute(user -> AggregateLifecycle.apply(new UserProfileChangedEvent(command.getId(), command.getUsername(), command.getRealName(), command.getDeliveryAddress())));
	}

}
