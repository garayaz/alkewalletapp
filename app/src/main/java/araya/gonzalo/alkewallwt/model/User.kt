package araya.gonzalo.alkewallwt.model

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val roleID: Long,
    val points: Long
)

class AppConstant{
    companion object{
        val users = listOf(
            User("Gonzalo", "Araya", "garayaz@gmail.com","12345",0,0),
        )
    }
}