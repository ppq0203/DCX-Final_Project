let images = ["./dist/img/dice-01.svg",
"./dist/img/dice-02.svg",
"./dist/img/dice-03.svg",
"./dist/img/dice-04.svg",
"./dist/img/dice-05.svg",
"./dist/img/dice-06.svg"];
let dice = document.querySelectorAll("img");

function roll(){
    dice.forEach(function(die){
        die.classList.add("shake");
    });
    setTimeout(function(){
        dice.forEach(function(die){
            die.classList.remove("shake");
        });
        let dieOneValue = Math.floor(Math.random()*6);
        let dieTwoValue = Math.floor(Math.random()*6);
        console.log(dieOneValue,dieTwoValue);
        document.querySelector("#die-1").setAttribute("src", images[dieOneValue]);
        document.querySelector("#die-2").setAttribute("src", images[dieTwoValue]);
        //document.querySelector("#total").innerHTML = "나온 주사위" + ( (dieOneValue +1) + (dieTwoValue + 1) );
    },
    1000
    );
}
//roll();