extern crate num;
extern crate image;

use num::Complex;
use std::str::FromStr;
use image::ColorType;
use image::png::PNGEncoder;
use std::fs::File;

fn main() {
    println!("Hello, world!");
}

// semicolon indicates that the value WON'T be returned. It is effectively
// suppressed in rust
// Eg: let a = {
//          let inner = 2
//          inner * inner // return to a. With `;` not return to a 
//      }
fn write_image(filename: &str, pixels: &[u8], bounds: (usize, usize))
    -> Result<(), std::io::Error>
{
    let output = File::create(filename)?;
    
    let encoder = PNGEncoder::new(output);
    encoder.encode(&pixels,
                bounds.0 as u32, bounds.1 as u32,
                ColorType::Gray(8))?; 
    //Each byte is an eight-bit grayscale value
    // ? is short hand for writing
    // match File::create(filename) {
    //  Ok(f) => { f }
    //  Err(e) => { Return Err(e); }:
    // }
    Ok(())
}

/// The function return type is Option<> with None or Some(i)
/// Option<T> is a generic type, often called an enum
/// Try to determine at `c` is in the Mandelbrot Set, using 
/// at most `limit` iterations to decide.
fn escape_time(c: Complex<f64>, limit: u32) -> Option<u32> {
    let mut z = Complex { re: 0.0, im: 0.0 };
    for i in 0..limit {
        z = z * z + c;
        if z.norm_sqr() > 4.0 {
            return Some(i);
        }
    }
    None
}

// For any type T that implements FromStr traits
fn parse_pair<T: FromStr>(s: &str, separator: char) -> Option<(T,T)>{
    match s.find(separator) {
        None => None,
        Some(index) => {
            // Expression &s[..index] are slices of string, type T try to parse
            // these string slices
            match(T::from_str(&s[..index]), T::from_str(&s[index + 1..])) {
                (Ok(l), Ok(r)) => Some((l,r)),
                _ => None
            }
        }
    }
}

fn parse_complex(s: &str) -> Option<Complex<f64>> {
    match parse_pair(s, ',') {
        // similar notation as JS with re and im initialization
        Some((re, im)) => Some(Complex { re, im }),
        None => None
    }
}

fn pixel_to_point(bounds: (usize, usize),
                  pixel: (usize, usize),
                  upper_left: Complex<f64>,
                  lower_right: Complex<f64>)
    -> Complex<f64>
{
    let (width, height) = (lower_right.re - upper_left.re,
                            upper_left.im - lower_right.im);
    Complex {
        // pixel.0 refers to the first element in a tuple
        // pixel.0 as f64 is Rust syntax for type conversion
        // Rust requires explicit casting
        re: upper_left.re + pixel.0 as f64 * width / bounds.0 as f64,
        im: upper_left.im - pixel.1 as f64 * height / bounds.1 as f64
        // pixel.1 increases as we go down but the imaginary component
        // increases as we go up
    }

}

fn render(pixels: &mut [u8],
        bounds: (usize, usize),
        upper_left: Complex<f64>,
        lower_right: Complex<f64>) {
    assert!(pixels.len() == bounds.0 * bounds.1);

    for row in 0 .. bounds.1 {
        for column in 0 .. bounds.0 {
            let point = pixel_to_point(bounds, (column, row),
                                        upper_left, lower_right);
            pixels[row * bounds.0 + column] = 
                match escape_time(point, 255) {
                    None => 0,
                    Some(count) => 255 - count as u8
                };
        }
    }
}


#[allow(dead_code)]
// rust linter for disable linting for this unused function
fn complex_square_add_loop(c: Complex<f64>) {
    let mut z = Complex { re: 0.0, im: 0.0};
    loop {
        z = z * z + c
    }
}

#[test]
fn test_parse_pair() {
    assert_eq!(parse_pair::<i32>("",','), None);
    assert_eq!(parse_pair::<i32>("10",','), None);
    assert_eq!(parse_pair::<i32>(",10",','), None);
    assert_eq!(parse_pair::<i32>("10,20",','),Some((10,20)));
    assert_eq!(parse_pair::<i32>("10,20xy",','), None);
    assert_eq!(parse_pair::<f64>("0.5x",'x'), None);
    assert_eq!(parse_pair::<f64>("0.5x1.5",'x'),Some((0.5, 1.5)));
}

#[test]
fn test_parse_complex() {
    assert_eq!(parse_complex("1.25,-0.0625"),
                Some(Complex { re: 1.25, im: -0.0625 }));
    assert_eq!(parse_complex(",-0.0625"), None);
}

#[test]
fn test_pixel_to_point() {
    assert_eq!(pixel_to_point((100,100), (25,75),
                            Complex {re: -1.0, im: 1.0},
                            Complex {re: 1.0, im: -1.0},
                Complex { re: -0.5, im: -0.5}));
}
