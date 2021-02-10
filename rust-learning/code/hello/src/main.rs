// 2 traits into scope. Any types implement the trait
// needs to be in-scope. A crate is a library/executable in Rust
use std::io::Write;
use std::str::FromStr;

fn main() {
    // Rust infers type as Vec<u64>
    let mut numbers = Vec::new();
    
    // an iterator
    for arg in std::env::args().skip(1) {
        // from_str returns a Result<> indicating success/failure
        // Result has 2 outcomes: Ok or Err
        // Rust doesn't have exceptions. Only Result or panic
        numbers.push(u64::from_str(&arg).expect("error parsing argument"));
    }

    if numbers.len() == 0 {
        // unwrap is a way to check that attempt to print error message
        // did not itself fail
        writeln!(std::io::stderr(), "Usage gcd NUMBER ...").unwrap();
    }
    
    // mut indicates mutable variable
    // without it, variables are immutable
    let mut d = numbers[0];
    // As we iterate, we want to tell Rust that ownership of the vector should
    // remain with numbers; we are only borrowing its elements for the loop
    // & borrows a referene to the vector's elements
    // * operator dereferences m, yielding its value it refers to
    for m in &numbers[1..] {
        d = gcd(d, *m);
    }

    println!("The greatest common divisor of {:?} is {}", numbers, d);
}

fn gcd(mut n: u64, mut m: u64) -> u64 {
    // ! character indicates a macro invocation
    assert!(n != 0 && m != 0);
    while m != 0 {
        if m < n {
            let t = m;
            m = n;
            n = t;
        }
        m = m % n;
    }
    n
}

#[test]
fn test_gcd() {
    assert_eq!(gcd(14,15), 1);

    assert_eq!(gcd(2 * 3 * 5 * 11 * 17,
                    3 * 7 * 11 * 13 * 19),
                    3 * 11);
}
