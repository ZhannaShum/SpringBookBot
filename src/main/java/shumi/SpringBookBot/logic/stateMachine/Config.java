package shumi.SpringBookBot.logic.stateMachine;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

public class Config extends EnumStateMachineConfigurerAdapter<ClientState, ClientEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<ClientState, ClientEvent> states) throws Exception {
        states.withStates()
                .initial(ClientState.CHOICE_COMMAND)
                .states(EnumSet.allOf(ClientState.class));
    }


    @Override
    public void configure(StateMachineTransitionConfigurer<ClientState, ClientEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(ClientState.CHOICE_COMMAND)
                .target(ClientState.WAITING_ANSWER)
                .event(ClientEvent.ENTER_SIMPLE_COMMAND)
                .and()
                .withExternal()
                .source(ClientState.CHOICE_COMMAND)
                .target(ClientState.ANSWERS_QUESTION_AUTHOR)
                .event(ClientEvent.ENTER_AUTHOR_COMMAND)
                /*.fu(new Action<ClientState, ClientEvent>() {
                    @Override
                    public void execute(StateContext<ClientState, ClientEvent> stateContext) {
                        askAuthor();
                    }
                })*/
                .and()
                .withExternal()
                .source(ClientState.ANSWERS_QUESTION_AUTHOR)
                .target(ClientState.WAITING_ANSWER)
                .event(ClientEvent.ENTER_DATA)
                .and()
                .withExternal()
                .source(ClientState.ANSWERS_QUESTION_AUTHOR)
                .target(ClientState.ANSWERS_QUESTION_GENRE)
                .event(ClientEvent.ENTER_GENRE_COMMAND)
                .action(askGenre())
                .and()
                .withExternal()
                .source(ClientState.ANSWERS_QUESTION_GENRE)
                .target(ClientState.ANSWERS_QUESTION_AUTHOR)
                .event(ClientEvent.ENTER_AUTHOR_COMMAND)
                .action(askAuthor())
                .and()
                .withExternal()
                .source(ClientState.CHOICE_COMMAND)
                .target(ClientState.ANSWERS_QUESTION_GENRE)
                .event(ClientEvent.ENTER_GENRE_COMMAND)
                .action(askGenre())
                .and()
                .withExternal()
                .source(ClientState.ANSWERS_QUESTION_GENRE)
                .target(ClientState.WAITING_ANSWER)
                .event(ClientEvent.ENTER_DATA)
                .and()
                .withExternal()
                .source(ClientState.WAITING_ANSWER)
                .target(ClientState.CHOICE_COMMAND)
                .event(ClientEvent.SEND_ANSWER);


    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<ClientState, ClientEvent> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true);
    }

}
