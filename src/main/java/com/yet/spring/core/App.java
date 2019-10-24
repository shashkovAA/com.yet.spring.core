package com.yet.spring.core;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class App {

	@Autowired
	private Client client;

	@Autowired
	@Qualifier("cacheFileEventLogger")
	private IEventLogger defaultLogger;

	@Autowired
	private LoggerMap loggers;

	public App() {};

	public App(Client client, IEventLogger defaultLogger, LoggerMap loggers) {
		this.client = client;
		this.defaultLogger= defaultLogger;
		this.loggers = loggers;
	}
	

	public void logEvent(/*Event event, EventType type*/ String msg) {
		
		/*IEventLogger logger = loggers.get(type);
		
		if (logger == null)
			logger = defaultLogger;
		
		logger.logEvent(event);*/
		
		//String message = msg.replaceAll(client.getId(), client.getFullName());
		//eventLogger.logEvent(message);
		//eventLogger.logEvent(event);
	}

	public void logEvent(Event event, EventType type) {

		System.out.println("Event type : " + type);

		IEventLogger logger = loggers.get(type);

		if (logger == null) {
			logger = defaultLogger;
			System.out.println("Event Logger is " + defaultLogger.getClass().getSimpleName());
		}
		else
			System.out.println("Event Logger is " + logger.getClass().getSimpleName());

		logger.logEvent(event);

		//defaultLogger.logEvent(event);
	}

	// 21.10.2019 Конфигурация Spring с помощью аннотаций
	public static void main(String[] args) throws InterruptedException {


		/*ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
				
		App app = (App) ctx.getBean("app");
		
		App app2 = (App) ctx.getBean(App.class);
		
		Client client2 = (Client) ctx.getBean("client");*/
		
		
		//System.out.println(EventType.INFO);
		//System.out.println(EventType.ERROR);
		
		//Client client2 = (Client) ctx.getBean("client");
		//System.out.println(client2.getFullName());


		ApplicationContext context =
				new AnnotationConfigApplicationContext(MyConfig.class);
		displayAllBeans(context);
		App app = (App) context.getBean(App.class);

		//App app = new App();
		//app.client = new Client("1", "John Smith");
		//app.eventLogger = new ConsoleEventLogger();
		
		//app.logEvent("Some event for user 3");
		//app2.logEvent("Some event for user 2");

		/*Event event = context.getBean(Event.class);
		event.setMsg(app.client.toString());
		app.logEvent(event);*/


		Event event;
		
		for (int i=0; i<4; i++) {
			event = context.getBean(Event.class);
			event.setMsg(app.client.toString());
			app.logEvent(event, EventType.ERROR);
			//app.logEvent(event, null);
			Thread.sleep(1000);
		}
				
		/*Thread.sleep(5000);

		context.close();*/
	}
	
	public static void displayAllBeans(ApplicationContext ctx) {
        String[] allBeanNames = ctx.getBeanDefinitionNames();
		System.out.println("Loaded beans into context :");
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }



}
