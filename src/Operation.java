/**
 * Created by Thomas Brocken on 12-9-2017.
 */
public enum Operation {

    ADD {
        @Override
        public LargeNumber execute(LargeNumber x, LargeNumber y) {
            return x.plus(y);
        }
    },
    SUBTRACT {
        @Override
        public LargeNumber execute(LargeNumber x, LargeNumber y) {
            return x.minus(y);
        }
    },
    MULTIPLY {
        @Override
        public LargeNumber execute(LargeNumber x, LargeNumber y) {
            return x.times(y);
        }
    },
    KARATSUBA {
        @Override
        public LargeNumber execute(LargeNumber x, LargeNumber y) {
            return x.karatsuba(y);
        }
    };

    public LargeNumber execute(LargeNumber x, LargeNumber y) {
        throw new AssertionError("execute is not implemented for " + name());
    }
}