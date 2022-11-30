package shumi.SpringBookBot.logic.stateMachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@SpringBootApplication
@EnableStateMachineFactory
public class Application implements CommandLineRunner {
    private final StateMachine<ClientState, ClientEvent>stateMachine;
    private String[] args;

    @Autowired
    public Application(StateMachine<ClientState, ClientEvent> stateMachine) {
        this.stateMachine = stateMachine;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String[] args) {
        this.args = args;
        stateMachine.start();
        stateMachine.sendEvent(ClientEvent.ENTER_SIMPLE_COMMAND);
        stateMachine.sendEvent(ClientEvent.ENTER_DATA);
        stateMachine.stop();
    }

    /*@Override
    public void run(String[] args) {
        boolean returnAccepted = stateMachine.sendEvent(ClientEvent.ENTER_SIMPLE_COMMAND);
        logger.info("return accepted: " + returnAccepted);
        boolean borrowAccepted = stateMachine.sendEvent(ClientEvent.ENTER_DATA);
        logger.info("borrow accepted: " + borrowAccepted);

}
     */
}
