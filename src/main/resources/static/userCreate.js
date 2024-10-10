 var createUserButton = document.querySelector("#createUserButton")
 var usernameTextbox = document.querySelector("#username")

 var users = []
 // or
 var aNewArray = new Array()

createUserButton.addEventListener('click', () => {
    var username = document.querySelector("#username")
    var password = document.querySelector("#password")

    if (username.value == '' || password.value == '') {
        alert("Please enter a username and a password")
    } else {
        console.log("Inputs look valid, proceed with form submission")
        var user = {
            "username" : username.value,
            "password" : password.value
        }
        users.push(user)
    }
})
