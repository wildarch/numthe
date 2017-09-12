/**
 * Created by Thomas Brocken on 12-9-2017.
 */
public enum Operation {
    Add {
        @Override
        public LargeNumber execute(LargeNumber x, LargeNumber y) {
            return x.plus(y);
        }
    },
    Subtract {
        @Override
        public LargeNumber execute(LargeNumber x, LargeNumber y) {
            return x.minus(y);
        }
    },
    Multiply {
        @Override
        public LargeNumber execute(LargeNumber x, LargeNumber y) {
            return x.times(y);
        }
    },
    Karatsuba {
        @Override
        public LargeNumber execute(LargeNumber x, LargeNumber y) {
            return x.karatsuba(y);
        }
    };

    public LargeNumber execute(LargeNumber x, LargeNumber y) {
        return null;
    }
}