use hyper::Client;
use hyper::{Body, Method, Request};

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error + Send + Sync>> {
    let req = Request::builder()
        .method(Method::POST)
        .uri("http://baidu.com")
        .body(Body::from("echo"))?;


    let client = Client::new();
    let resp = client.request(req).await?;
    println!("Response: {}", resp.status());
    println!("{:?}", hyper::body::to_bytes(resp.into_body()).await.unwrap());

    Ok(())
}