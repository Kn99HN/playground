const hummus = function(factor) {
  const ingredient = function(amount, unit, name) {
    let ingredientAmount = amount * factor;
    if(ingredientAmount > 1) {
      unit += "s";
    }
    console.log(`${ingredientAmount} ${unit} ${name}`);
  };
  ingredient(1, "can", "chickpeas");
  ingredient(0.25, "cup", "tahini");
  ingredient(0.25, "cup", "lemon juice");
  ingredient(1, "clove", "garlic");
  ingredient(2, "tablespoon", "olive oil");
  ingredient(0.5, "teaspoon", "cumin");
}

hummus(5);

console.log("The future says:", future());

function future() {
  return "You'll never have flying cars";
}


// Minimum

const min = (a, b) => {
  return a < b ? a : b;
}

console.log(min(0,10));

console.log(min(0, -10));

// Recursion

const isEven = (number) => {
  if(number === 0) return true;
  if(number === 1) return false;
  if(number >= 2) return isEven(number - 2);
  if(number < 0) return isEven(number + 2);
}

console.log(isEven(50));
console.log(isEven(75));
console.log(isEven(-1));
console.log(isEven(-2));

// Bean Counting

const countChar = (sentences, letter) => {
  let counter = 0;
  for(let i = 0; i < sentences.length; i++) {
    if(sentences[i] === letter) {
      counter += 1;
    }
  }
  return counter;
}

const countBs = (sentences) => {
  let counter = 0;
  for(let i = 0; i < sentences.length; i++) {
    if(sentences[i] === "B") {
      counter += 1;
    }
  }
  return counter;
}

console.log(countBs("BBC"));
console.log(countChar("kakerlak", "k"));
