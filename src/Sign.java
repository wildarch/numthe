/**
 * @author Ruben Schellekens
 */
public enum Sign {

    POSITIVE(LargeNumber.getPOSITIVE(), ""),
    NEGATIVE(LargeNumber.getNEGATIVE(), "-");

    private int intRepresentation;
    private String character;

    Sign(int intRepresentation, String character) {
        this.intRepresentation = intRepresentation;
        this.character = character;
    }

    public int getIntRepresentation() {
        return intRepresentation;
    }

    public String getCharacter() {
        return character;
    }
}
