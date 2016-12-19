package problems;

import java.util.List;

/**
 * Created by hojjatimani on 12/16/2016 AD.
 */
public interface Problem {

    interface State {

    }

    int getValue(State s);

    boolean isGoal(State s);

    State getInitialState();

    List<? extends State> getSuccessors(State state);
}