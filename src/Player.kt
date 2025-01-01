class Player {
    private var name = ""
        set(value) {
            field = value
        }
    var itemsList = mutableListOf<String>()
    var health = 0
    var isHandcuffed = false

    fun dealDamage(damage: Int){
        this.health -= damage
    }

    fun replenishHealth(){
        this.health += 1
    }

    fun printInfo(){
        println("Name: $name, Age: $health")
    }



}