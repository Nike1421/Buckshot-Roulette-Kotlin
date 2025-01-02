data class IndexedShell(val position: Int, val shellType: String)

class Shotgun {
    private val slots = 8
    private var shotgunBarrel = mutableListOf<String>()
    var damage = 1

    fun addRandomShells(){
        val numberOfShells = (2..slots).random()
        val liveShellCount = (1..(numberOfShells*0.75).toInt()).random()
        val blankShellCount = numberOfShells - liveShellCount

        displayUnShuffledBarrel(liveShellCount = liveShellCount, blankShellCount = blankShellCount)

        for(i in 0 ..< liveShellCount){
            shotgunBarrel.add("Live")
        }

        for (i in 0 ..< blankShellCount){
            shotgunBarrel.add("Blank")
        }

        shotgunBarrel.shuffle()
    }

    private fun displayUnShuffledBarrel(liveShellCount: Int, blankShellCount: Int){
        println("The Shotgun Barrel Contains $liveShellCount Live and $blankShellCount Blank Shells in a Random Order.")
    }

    fun peekBarrel(): String{
        return shotgunBarrel[shotgunBarrel.lastIndex]
    }

    fun peekShellAtRandomIndex(): IndexedShell {
        val position = (0..<shotgunBarrel.size).random()
        return IndexedShell(position, shotgunBarrel[shotgunBarrel.size - position - 1])
    }

    fun rackBarrel(): String{
        val poppedShell = shotgunBarrel.removeLast()
        return poppedShell
    }

    fun shoot(): String{
        val poppedShell = shotgunBarrel.removeLast()
        return poppedShell
    }

    fun invertLastShellInBarrel(){
        val poppedShell = rackBarrel()
        shotgunBarrel.add(shotgunBarrel.lastIndex, if (poppedShell == "Blank") "Live" else "Blank")
    }

    fun displayShotsInBarrel(){
        println(shotgunBarrel)
    }

    fun doubleDamage(){
        damage = 2
    }

    fun resetDamage(){
        damage = 1
    }

    fun shotgunRoundCount(): Int{
        return shotgunBarrel.size
    }
}

fun main() {
    val shotgun = Shotgun()
    shotgun.addRandomShells()
    shotgun.displayShotsInBarrel()
//    println(shotgun.popBarrel())
    shotgun.invertLastShellInBarrel()
    println(shotgun.peekShellAtRandomIndex())
    shotgun.displayShotsInBarrel()
}