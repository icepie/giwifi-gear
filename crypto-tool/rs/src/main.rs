extern crate aes;
extern crate block_modes;
extern crate getopts;
use aes::Aes128;
use block_modes::block_padding::ZeroPadding;
use block_modes::{BlockMode, Cbc};
use getopts::Options;
use std::env;

// Default Key
static DEFAULT_KEY: &'static str = "1234567887654321";

fn print_usage(program: &str, opts: Options) {
    let brief = format!("Usage: {} [options]", program);
    print!("{}", opts.usage(&brief));
}

fn encrypted_string(text: &str, iv: &str, key: &str) -> String {
    let iv = iv.as_bytes();
    let mode = Cbc::<Aes128, ZeroPadding>::new_var(key.as_bytes(), &iv).unwrap();
    let enc_buf = mode.encrypt_vec(text.as_bytes());
    base64::encode(enc_buf)
}

fn main() {
    let exit_code = real_main();
    std::process::exit(exit_code);
}

fn real_main() -> i32 {
    let args: Vec<String> = env::args().collect();
    let program = args[0].clone();

    let mut opts = Options::new();
    opts.optopt("t", "", "the text to be encrypted", "TEXT");
    opts.optopt("i", "", "iv (initialization vector)", "IV");
    opts.optopt("k", "", "the key (default \"1234567887654321\")", "KEY");
    opts.optflag("h", "help", "print this help menu");
    let matches = match opts.parse(&args[1..]) {
        Ok(m) => m,
        Err(f) => {
            panic!(f.to_string())
        }
    };
    if matches.opt_present("h") {
        print_usage(&program, opts);
        return 1;
    }

    if !matches.opt_present("t") || !matches.opt_present("i") {
        print_usage(&program, opts);
        return 1;
    }

    let key = if matches.opt_present("k") {
        matches.opt_str("k").unwrap().trim().to_string()
    } else {
        DEFAULT_KEY.to_string()
    };

    let text = matches.opt_str("t").unwrap().trim().to_string();
    let iv = matches.opt_str("i").unwrap().trim().to_string();

    if iv.len() != 16 || key.len() != 16 {
        print!("error");
        return 1;
    }

    let result = encrypted_string(text.as_str(), iv.as_str(), key.as_str());

    print!("{}", result);
    return 0;
}
