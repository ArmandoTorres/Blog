function validateEmail(item){

      var email       = document.getElementById(item); 
      var patronEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

      if (!patronEmail.test(email.value)) {
         alert("The field email is invalid!");
         email.focus();
         return false;
      }
      
    return true;
}