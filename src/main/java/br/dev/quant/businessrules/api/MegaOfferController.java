package br.dev.quant.businessrules.api;

import java.util.Arrays;
import java.util.Collection;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MegaOfferController {
	
	Logger logger = LoggerFactory.getLogger(MegaOfferController.class);
	
	@Autowired
	private KieContainer container;
	
	
	@PostMapping("/statefulOrder")
	public Order orderNow(@RequestBody Order order) {		
		logger.info("stateful session created...");

		KieSession session = container.newKieSession();		
		session.insert(order);
		session.fireAllRules();
		
		Collection<? extends Object> processInstances = session.getObjects();
		logger.debug("Collection size: " + processInstances.size());		
		
		for(Object element: processInstances){
			logger.debug("Class: " + element.getClass());
			if(element instanceof Message){
				Message message = (Message) element;
				order.getList().add(message);
			}
			if(element instanceof FactHandle){
				logger.debug("delete FactHandle");
				session.delete((FactHandle) element);
			}
		}
		
		logger.debug("Final Collection size: " + processInstances.size());
		session.dispose();		
		return order;
	}
	
	@PostMapping("/statelessOrder")
	public Order statelessOrderNow(@RequestBody Order order){
		logger.info("stateless session created...");

		StatelessKieSession session = container.newStatelessKieSession();
		session.execute(Arrays.asList(new Object[] { order }));
		return order;
	}
}
