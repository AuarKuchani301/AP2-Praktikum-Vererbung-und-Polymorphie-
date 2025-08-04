
interface Adoption{
    val days : Int
    val price : Double
    val benefits : List <String >
}
class LimitedAdoption( private val tageEntgegen : Int): Adoption{
    override val days: Int
        get() = tageEntgegen
    override val price: Double = 5.0 * tageEntgegen
    override val benefits: List<String> = listOf("Besuchen", "Füttern")
}
open class UnlimitedAdoption( var personname : String): Adoption{
    override val days: Int
        get() = Int.MAX_VALUE

    override val price: Double
        get() = 1000.0
    override val benefits: List<String>
        get() = listOf("Besuchen", "Füttern", "Streicheln" , "Gedenktafel mit dem Namen der Person am Gehege")
}


abstract class Animal (val species : String , val age : Int )  {

    abstract fun makeSound () : String
    val listeVonPatenschaften = mutableListOf<Adoption>()

    fun addAdoption(adoption: Adoption) = listeVonPatenschaften.add(adoption)
    override fun toString(): String = species
}
open class Pet(
    species : String ,
    age : Int ,
    val owner : String ,
    val name : String
) :Animal(species ,age) {
    override fun toString(): String = name
    override fun makeSound(): String = "$name sagt etwas"
}
class Cat(
    species : String ,
    age : Int ,
    owner : String ,
    name : String
) : Pet(species,age, owner,name) {
    override fun toString(): String = "Cat"
    override fun makeSound(): String = super.makeSound() + " Miau !"
    //Lucy kratzt an den Möbeln und sagt Lucy sagt etwas : Miau}
}
    class Dog(
        species: String,
        age: Int,
        owner: String,
        name: String,
        val wagsTail: Boolean
    ) : Pet(species, age, owner, name) {
        override fun toString(): String = name
        override fun makeSound(): String = super.makeSound() + "Wuff"

        // Emma bringt das Spielzeug zurück und freut sich !
        fun fetchToy(): String =
            if (wagsTail == true) " $name bringt das Spielzeug zurück Und Freut sich." else " $name bringt das Spielzeug zurück und Freut sich nicht."
//    fun fetchToy () : String {    // andere Schreibweise.
//           if (wagsTail == true)
//               return " $name bringt das Spielzeug zurück Und Freut sich."
//           else
//               return " $name bringt das Spielzeug zurück und Freut sich nicht."
//    }
    }

    open class Bird(
        species: String,
        age: Int,
        val nocturnal: Boolean
    ) : Animal(species, age) {

        override fun makeSound(): String = "$species zwitschert oder singt"
        override fun toString(): String = "Bird"

    }

    class Parrot(
        species: String,
        age: Int,
        nocturnal: Boolean = false,
        val knownWords: List<String>
    ) : Bird(species, age, nocturnal) {

        override fun toString(): String = "Parrot"
        override fun makeSound(): String = "$species ahmt Wörter nach: ${knownWords.joinToString { it }}"
    }


    class Owl(
        species: String,
        age: Int,
        nocturnal: Boolean = true,
        val prey: List<String>
    ) : Bird(species, age, nocturnal) {

        override fun makeSound(): String = "$species zwitschert oder singt"

        // Eule fliegt lautlos
        fun flySilently(): String = " $species fliegt lautlos."

        // Eule fliegt lautlos und jagt Maus , Maulwurf
        fun hunts(): String = flySilently() + "und jagt" + prey.joinToString { it }
        override fun toString(): String = "Owl"
    }

    class Zoo() {
        val animalList = mutableListOf<Animal>()

        override fun toString(): String {
            return super.toString() + "$animalList"
        }

        fun addAnimal(animal: Animal) = animalList.add(animal)
        fun removeAnimal(animal: Animal) = animalList.remove(animal)
        fun getTotalAdoptionMoney(): Double {
            var totalMoney = 0.0
            for (animal in animalList) {
                for (adoption in animal.listeVonPatenschaften) {
                    totalMoney += adoption.price
                }
            }
            return totalMoney
        }

        fun getBirdsInZoo(): List<Bird> = animalList.filterIsInstance<Bird>()

        // Oder so..
//      fun getBirdsInZoo() : List<Bird>{
//          val birdList = mutableListOf<Bird>()
//          for (animal in animalList){
//              if (animal is Bird)
//                  birdList.add(animal)
//          }
//          return birdList
//      }
        fun getUnlimitedAdopters(): List<String> {
            val adopters = mutableListOf<String>()
            for (animal in animalList) {
                for (adoption in animal.listeVonPatenschaften) {
                    if (adoption is UnlimitedAdoption) {
                        adopters.add(adoption.personname)
                    }
                }
            }
            return adopters
        }

        fun getNumberOfGreetingParrots(words: List<String>): Int {
            var count = 0
            for (animal in animalList) {
                if (animal is Parrot) {
                    if (words.any { it in animal.knownWords }) {
                        count++
                    }
                }
            }
            return count
        }

        fun getBabies(): List<Animal> {
            val babyList = mutableListOf<Animal>()
            for (animal in animalList) {
                if (animal.age <= 1) {
                    babyList.add(animal)
                }
            }
            return babyList
        }
    }


    fun main() {
        println("------------------------------------------")
//        val dog : Dog= Dog (" Golden Retriever ", 6 , " Anja ", " Emma ", true )
//        val cat = Cat (" Britisch Kurzhaar ", 0, " Sascha ", " Lucy ")
//        val bird = Bird (" Amsel ", 0, false )
//        val owl = Owl (" Eule ", 5, true , listOf (" Maus ", " Maulwurf ") )
//        val parrot = Parrot (" Blaupapagei ", 0, false , listOf (" Hallo ", " Guten Tag ", " Ich mag Pizza ", " Tsch üß") )
//
//        println ( parrot.makeSound () ) // Blaupapagei ahmt Wörter nach : Hallo , Guten Tag , Ich mag Pizza , Tsch üß
//        println ( cat. scratchFurniture () ) // Lucy kratzt an den Möbeln und sagt Lucy sagt etwas : Miau !
//        println ( dog.fetchToy () ) // Emma bringt das Spielzeug zurück und freut sich !
//        println ( owl.flySilently () ) // Eule fliegt lautlos
//        println ( owl . hunts () ) // Eule fliegt lautlos und jagt Maus , Maulwurf
//
//        println("------------------------------------------")
//        parrot.addAdoption(UnlimitedAdoption("Mario"))
//
        println("------------------------------------------")
        val bird = Bird(" Amsel ", 0, false)
        val owl = Owl(" Eule ", 5, true, listOf(" Maus ", " Maulwurf "))
        val dog = Dog(" Golden Retriever ", 6, " Anja ", " Emma ", true)
        dog.addAdoption(UnlimitedAdoption(" Sandy "))
        val cat = Cat(" Britisch Kurzhaar ", 0, " Sascha ", " Lucy ")
        cat.addAdoption(LimitedAdoption(60))
        cat.addAdoption(UnlimitedAdoption(" Sandy "))
        val parrot = Parrot(" Blaupapagei ", 0, false, listOf(" Hallo ", " Guten Tag ", " Ich mag Pizza ", " Tsch üß"))
        parrot.addAdoption(UnlimitedAdoption(" Mario "))
        val zoo = Zoo()
        zoo.addAnimal(dog)
        zoo.addAnimal(cat)
        zoo.addAnimal(bird)
        zoo.addAnimal(owl)
        zoo.addAnimal(parrot)
        println(zoo.getTotalAdoptionMoney()) // 3300
        println(zoo.getBirdsInZoo()) // Bird , Owl , Parrot
        println(zoo.getUnlimitedAdopters()) // Sandy . Mario
        println(zoo.getNumberOfGreetingParrots(listOf(" Hallo ", " Guten Tag "))) // 1
        println(zoo.getBabies()) // Cat , Bird , Parrot


}






