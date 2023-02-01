
let numbers = [2, 5, 12, 13, 15, 18, 22];

class Car {
    constructor(num) {
        this.num = num;
    }

    isEven() {
    console.log(this.num + 'は偶数です');
}   

    getNumGas() {
    console.log( ` ガソリンは〇〇です。ナンバーは${this.num}です ` );
}   


}


for (let i = 0; i < numbers.length; i++) {
  if (numbers[i] % 2 === 0) {
	let even = new Car(numbers[i]);
	//console.log(even.num);
	even.isEven();
	even.getNumGas();
  }
}

