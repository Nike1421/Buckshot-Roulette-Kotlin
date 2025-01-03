import kotlin.contracts.contract

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
            if (playerOneItems.size < 8) playerOneItems.add(itemsList.random())
            if (playerTwoItems.size < 8) playerTwoItems.add(itemsList.random())
        }
    }

    fun printPlayerItems(){
        println(playerOneItems)
        println(playerTwoItems)
    }

    fun printCurrentPlayerItems(){
        if (currentPlayer == playerOne) println(playerOneItems) else println(playerTwoItems)
    }

    fun switchCurrentPlayer(){
        if (otherPlayer.isHandcuffed != true){
            otherPlayer = currentPlayer
            currentPlayer = if(currentPlayer == playerOne) playerTwo else playerOne
        }
        otherPlayer.isHandcuffed = false
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

    fun playerUseItems(){
        var itemInput = 0
        var currentPlayerItems = if (currentPlayer == playerOne) playerOneItems else playerTwoItems
        while (currentPlayerItems.size > 0){
            print("Enter the item you want to use or enter -1 to not use any items: ")
            itemInput = readln().toInt()
            if (itemInput == -1){
                break
            }
            val chosenItem = currentPlayerItems[itemInput]
            when (chosenItem) {
                "Magnifying Glass" -> magnifyingGlassAction()
                "Cigarette Pack" -> cigarettePackAction()
                "Beer" -> beerAction()
                "Handsaw" -> handsawAction()
                "Handcuffs" -> handcuffsAction()
                "Burner Phone" -> burnerPhoneAction()
                "Inverter" -> inverterAction()
            }
            currentPlayerItems.removeAt(itemInput)
            printCurrentPlayerItems()
        }
    }

    fun handleShot(shotPlayer: Player){
        val shotShell = shotgun.shoot()
        if (shotShell == "Live"){
            println("The Shell Was Live")
            shotPlayer.dealDamage(shotgun.damage)
            switchCurrentPlayer()
        } else {
            if (shotPlayer == currentPlayer){
                println("The Shell Was Blank. Play Again")
            } else {
                println("The Shell Was Blank. Switch Player")
                switchCurrentPlayer()
            }
        }
        shotgun.resetDamage()
    }
}

fun initPlayers(game: Game){
    println("Player One!")
    print("Please Enter Your Name: ")
    game.playerOne.name = readln()

    println("Player Two!")
    print("Please Enter Your Name: ")
    game.playerTwo.name = readln()
}



fun main() {
    val game = Game()
    var userShotChoice = -1
    var shotPlayer: Player
    initPlayers(game = game)
    game.populatePlayerItems()
    game.printPlayerItems()
    game.shotgun.addRandomShells()
    while (game.currentPlayer.health != 0 && game.otherPlayer.health != 0){
        println("Turn: ${game.currentPlayer.name}")
        print("Your Items: ")
        game.printCurrentPlayerItems()
        game.playerUseItems()
        println("You have the shotgun. Enter 1 to shoot yourself or 2 to shoot the opponent.")
        userShotChoice = readln().toInt()
        shotPlayer = if (userShotChoice == 1) game.currentPlayer else game.otherPlayer
        game.handleShot(shotPlayer)
        game.currentPlayer.printInfo()
        if (game.shotgun.shotgunRoundCount() == 0){
            game.shotgun.addRandomShells()
            game.populatePlayerItems()
        }
    }
}