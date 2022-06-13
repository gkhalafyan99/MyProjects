document.addEventListener('DOMContentLoaded', function() {
    document.querySelector("#username").onkeyup = function() {
        if(document.querySelector("#username").value.length < 1) {
            document.querySelector("#submit").disabled = true;
        } else{
            if(document.querySelector("#password").value.length < 6){
                document.querySelector("#submit").disabled = true;
            } else {
                if(document.querySelector("#confirm").value.length < 6){
                    document.querySelector("#submit").disabled = true;
                } else {
                    if(document.querySelector("#email").value.length < 1){
                        document.querySelector("#submit").disabled = true;
                    } else {
                        document.querySelector("#submit").disabled = false;
                    }
                }
            }
        }

    };

    document.querySelector("#password").onkeyup = function() {
        if(document.querySelector("#password").value.length < 6) {
            document.querySelector("#submit").disabled = true;
        } else {
            if(document.querySelector("#username").value.length < 1){
                document.querySelector("#submit").disabled = true;
            } else {
                if(document.querySelector("#confirm").value.length < 6){
                    document.querySelector("#submit").disabled = true;
                } else {
                    if(document.querySelector("#email").value.length < 1){
                        document.querySelector("#submit").disabled = true;
                    } else {
                        document.querySelector("#submit").disabled = false;
                    }
                }
            }
        }
    };

    document.querySelector("#confirm").onkeyup = function() {
        if(document.querySelector("#confirm").value.length < 6) {
            document.querySelector("#submit").disabled = true;
        } else {
            if(document.querySelector("#username").value.length < 1){
                document.querySelector("#submit").disabled = true;
            } else {
                if(document.querySelector("#password").value.length < 6){
                    document.querySelector("#submit").disabled = true;
                } else {
                    if(document.querySelector("#email").value.length < 1){
                        document.querySelector("#submit").disabled = true;
                    } else {
                        document.querySelector("#submit").disabled = false;
                    }
                }
            }
        }
    };

    document.querySelector("#email").onkeyup = function() {
        if(document.querySelector("#email").value.length < 1) {
            document.querySelector("#submit").disabled = true;
        } else {
            if(document.querySelector("#username").value.length < 6){
                document.querySelector("#submit").disabled = true;
            } else {
                if(document.querySelector("#password").value.length < 6){
                    document.querySelector("#submit").disabled = true;
                } else {
                    if(document.querySelector("#confirm").value.length < 6){
                        document.querySelector("#submit").disabled = true;
                    } else {
                        document.querySelector("#submit").disabled = false;
                    }
                }
            }
        }
    };   
})