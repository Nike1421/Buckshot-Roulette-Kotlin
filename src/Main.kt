class Game {
    val roundsCount = 3
    val itemsList = listOf<String>("Magnifying Glass", "Cigarette Pack", "Beer", "Handsaw", "Handcuffs", "Burner Phone", "Inverter")
    var currentRoundNumber = 0

    val playerOne: Player = Player()
    val playerTwo: Player = Player()
    val shotgun: Shotgun = Shotgun()

    var currentPlayer = playerOne
    var otherPlayer = playerTwo

    var playerOneItems = mutableListOf<String>()
    var playerTwoItems = mutableListOf<String>()

    fun populatePlayerItems(){
        val itemsCount = (3..5).random()
        for (itemIndex in (0..itemsCount)){
            playerOneItems.add(itemsList.random())
            playerTwoItems.add(itemsList.random())
        }
    }

    fun printPlayerItems(){
        println(playerOneItems)
        println(playerTwoItems)
    }

    fun switchCurrentPlayer(){
        otherPlayer = currentPlayer
        currentPlayer = if(currentPlayer == playerOne) playerTwo else playerOne
    }

    fun burnerPhoneAction(){
        val shell = shotgun.peekShellAtRandomIndex()
        println("The Shell at ${shell.position} is ${shell.shellType}.")
    }

    fun magnifyingGlassAction(){
        println("The Current Shell is ${shotgun.peekBarrel()}")
    }

    fun cigarettePackAction(){
        currentPlayer.replenishHealth()
    }

    fun beerAction(){
        println("Shotgun Racked. The Racked Shell Was ${shotgun.rackBarrel()}")
    }

    fun handsawAction(){
        shotgun.doubleDamage()
        println("Shotgun Damage Doubled.")
    }

    fun handcuffsAction(){
        otherPlayer.isHandcuffed = true
    }

    fun inverterAction(){
        shotgun.invertLastShellInBarrel()
        println("Top Shell In The Barrel Has Been Inverted")
    }
}

//fun initPlayers(game: Game){
//    println("Player One!")
//    print("Please Enter Your Name: ")
//    game.playerOne
//}

fun main() {
    val game = Game()
    game.populatePlayerItems()
    game.printPlayerItems()
    while (game.currentPlayer.health != 0 && game.otherPlayer.health != 0){

    }
}