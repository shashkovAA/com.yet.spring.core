package com.yet.spring.core;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	Client client;
	//ConsoleEventLogger eventLogger;
	IEventLogger defaultLogger;
	Map<EventType, IEventLogger> loggers;

	public App() {};
	
	public App(Client client, IEventLogger defaulltLogger, Map<EventType, IEventLogger> loggers) {
		this.client = client;
		this.defaultLogger = defaulltLogger;
		this.loggers = loggers;
	}
	
	/*public void logEvent(String msg) {
		
		String message = msg.replaceAll(client.getId(), client.getFullName());
		eventLogger.logEvent(message);
	}*/
	
	public void logEvent(Event event, EventType type) {
		
		IEventLogger logger = loggers.get(type);
		
		if (logger == null)
			logger = defaultLogger;
		
		logger.logEvent(event);
		
		//String message = msg.replaceAll(client.getId(), client.getFullName());
		//defaultLogger.logEvent(event);
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
				
		App app = (App) ctx.getBean("app");
		
		App app2 = (App) ctx.getBean(App.class);
		
		Client client2 = (Client) ctx.getBean("client");
		
		
		//System.out.println(EventType.INFO);
		//System.out.println(EventType.ERROR);
		
		//Client client2 = (Client) ctx.getBean("client");
		//System.out.println(client2.getFullName());
		
		//App app = new App();
		//app.client = new Client("1", "John Smith");
		//app.eventLogger = new ConsoleEventLogger();
		
		//app.logEvent("Some event for user 1");
		//app2.logEvent("Some event for user 2");
		
		//Event event = ctx.getBean(Event.class);
		//event.setMsg(client2.toString());
		//displayAllBeans(ctx);
		//app2.logEvent(event);
		Event event;
		
		for (int i=0; i<4; i++) {
			event = ctx.getBean(Event.class);
			event.setMsg(client2.toString());
			app2.logEvent(event, EventType.ERROR);
			Thread.sleep(1000);
		}
		
		/*event = ctx.getBean(Event.class);
		event.setMsg(client2.toString());
		app2.logEvent(event, null);*/
				
		Thread.sleep(2000);
		
		ctx.close();
	}
	
	public static void displayAllBeans(ConfigurableApplicationContext ctx) {
        String[] allBeanNames = ctx.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }

}
