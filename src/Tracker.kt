/**
 * @author Ruben Schellekens
 */
object Tracker {

    /**
     * Tracks:
     * - Index 0: Additions.
     * - Index 1: Subtractions.
     * - Index 2: Elementary multiplications.
     */
    private var count: Array<Long> = arrayOf(0L, 0L, 0L)

    /**
     * Resets all trackers.
     *
     * @return The state before resetting (see [count]).
     */
    @JvmStatic
    fun reset(): Array<Long> {
        val result = count.clone()
        count = arrayOf(0L, 0L, 0L)
        return result
    }

    /**
     * Adds 1 addition to the tracker.
     *
     * @return The new amount of additions.
     */
    @JvmStatic
    fun addition() = ++count[0]

    /**
     * Adds 1 subtraction to the tracker.
     *
     * @return The new amount of subtractions.
     */
    @JvmStatic
    fun subtraction() = ++count[1]

    /**
     * Adds 1 elementary multiplcation to the tracker.
     *
     * @return The new amount of multiplications.
     */
    @JvmStatic
    fun multiplication() = ++count[2]

    /**
     * @return The amount of tracked additions.
     */
    @JvmStatic
    fun getAdditions() = count[0]

    /**
     * @return The amount of tracked subtractions.
     */
    @JvmStatic
    fun getSubtractions() = count[1]

    /**
     * @return The amount of tracked elementary multiplications.
     */
    @JvmStatic
    fun getMultiplications() = count[2]

    /**
     * @return The total amount of elementary operations (add, subtract and multiplication).
     */
    @JvmStatic
    fun getTotal() = count[0] + count[1] + count[2]
}