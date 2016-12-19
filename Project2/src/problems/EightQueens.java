package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hojjatimani on 12/16/2016 AD.
 */
public class EightQueens implements Problem {
    boolean BIG_MOVE = true;
    boolean pruneBadStates;

    public EightQueens(boolean pruneBadStates) {
        this.pruneBadStates = pruneBadStates;
    }

    @Override
    public int getValue(Problem.State s) {
        int val = 0;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (((State) s).cells[i][j])
                    val -= ((State) s).getNumberOfAttacksToCell(i, j);
        return val / 2;
    }

    @Override
    public boolean isGoal(Problem.State s) {
        return getValue(s) == 0;
    }

    @Override
    public Problem.State getInitialState() {
        return State.newRandom(pruneBadStates);
    }

    @Override
    public List<State> getSuccessors(Problem.State state) {
        List<State> states = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (((State) state).cells[i][j])
                    states.addAll(getSuccessorsByMovingQueen(((State) state), i, j));
            }
        }
        return states;
    }

    private List<State> getSuccessorsByMovingQueen(State state, int x, int y) {
        List<State> res = new ArrayList<>(6);
        if (pruneBadStates) {
            if (BIG_MOVE) {
                for (int i = 0; i < 8; i++) {
                    if (i != y)
                        res.add(State.from(state).moveQueen(x, y, x, i));
                }
            } else {
                if (y > 0)
                    res.add(State.from(state).moveQueen(x, y, x, y - 1));
                if (y < 7)
                    res.add(State.from(state).moveQueen(x, y, x, y + 1));
            }
        } else {
            if (x > 0) {
                if (!state.cells[x - 1][y])
                    res.add(State.from(state).moveQueen(x, y, x - 1, y));
                if (y > 0 && !state.cells[x - 1][y - 1])
                    res.add(State.from(state).moveQueen(x, y, x - 1, y - 1));
                if (y < 7 && !state.cells[x - 1][y + 1])
                    res.add(State.from(state).moveQueen(x, y, x - 1, y + 1));
            }
            if (x < 7 && !state.cells[x + 1][y]) {
                res.add(State.from(state).moveQueen(x, y, x + 1, y));
                if (y > 0 && !state.cells[x + 1][y - 1])
                    res.add(State.from(state).moveQueen(x, y, x + 1, y - 1));
                if (y < 7 && !state.cells[x + 1][y + 1])
                    res.add(State.from(state).moveQueen(x, y, x + 1, y + 1));
            }
            if (y > 0 && !state.cells[x][y - 1])
                res.add(State.from(state).moveQueen(x, y, x, y - 1));
            if (y < 7 && !state.cells[x][y + 1])
                res.add(State.from(state).moveQueen(x, y, x, y + 1));
        }
        return res;
    }

    /**
     * @param from inclusive
     * @param end  exclusive
     * @return a newRandom int in range [from, end)
     */
    public static int randInt(int from, int end) {
        if (from > end)
            return -1;
        int diff = end - from;
        return from + (int) (Math.random() * diff);
    }

    static class State implements Problem.State {
        private boolean[][] cells = new boolean[8][8]; //(cells[i][j] == true) <=> there is a queen in cell[i][j]

        private static State newRandom(boolean pruneBadStates) {
            State s = new State();
            if (pruneBadStates) {
                for (int i = 0; i < 8; i++)
                    s.cells[i][randInt(0, 8)] = true;
            } else {
                int x, y, queens = 0;
                while (queens < 8) {
                    x = randInt(0, 8);
                    y = randInt(0, 8);
                    if (!s.cells[x][y]) {
                        s.cells[x][y] = true;
                        queens++;
                    }
                }
            }
            return s;
        }

        private static State from(State src) {
            State res = new State();
            for (int i = 0; i < 8; i++)
                System.arraycopy(src.cells[i], 0, res.cells[i], 0, 8);
            return res;
        }

        private int getNumberOfAttacksToCell(int x, int y) {
            int res = 0;
            for (int i = 0; i < 8; i++) {
                if (x != i && cells[i][y])
                    res++;
                if (y != i && cells[x][i])
                    res++;
            }
            for (int i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++)
                if (cells[i][j])
                    res++;
            for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--)
                if (cells[i][j])
                    res++;
            for (int i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++)
                if (cells[i][j])
                    res++;
            for (int i = x + 1, j = y - 1; i < 8 && j >= 0; i++, j--)
                if (cells[i][j])
                    res++;
            return res;
        }

        private State moveQueen(int x, int y, int toX, int toY) {
            assert cells[x][y] : "No queen to move!";
            assert !cells[toX][toY] : "There is another queen in destination!";
            cells[x][y] = false;
            cells[toX][toY] = true;
            return this;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    sb.append(" ").append(cells[i][j] ? 1 : 0).append(" ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}
