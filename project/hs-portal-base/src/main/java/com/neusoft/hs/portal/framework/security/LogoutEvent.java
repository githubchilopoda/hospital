package com.neusoft.hs.portal.framework.security;

import org.springframework.context.ApplicationEvent;

public class LogoutEvent extends ApplicationEvent {

	public LogoutEvent(Object source) {
		super(source);
	}
}
