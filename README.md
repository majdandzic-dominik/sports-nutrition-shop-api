# sports-nutrition-shop-api
API for whey protein prices from different stores

This API currently scrapes data for whey protein products from GymBeam and Polleo Sport.

## How to run
The API isn't available online so you will need to run it locally. For that you will need Java 17 (or later) installed. You will also need Firefox installed since the scraping is done using Selenium.

Download the "nutrition_api.jar" file and run it. To run it using cmd, inside cmd navigate to the folder where you saved the file using the command "cd folderpath".
After that type the command "java -jar nutrition_api.jar". To close the application inside cmd press ctrl+c.

You can also run the jar file simply by opening the file but then you will need to close it using task manager.

### NOTE - It takes a while for the data to be collected (around 5-10 minutes) before the API becomes available.

## Usage
The API runs on port 8080 so to access it you will need to type in the url "http://localhost:8080" into your internet browser.

Currently there is only one url path "/protein". (To access them simply add them to the end of initial url, "http://localhost:8080/protein")

"/protein" returns two lists of available whey protein products. Each list is for a different store.

Output:
```json
{
  "GymBeam": [
    {
      "name": "True Whey - GymBeam",
      "price": "43,95 EUR",
      "amount": "2500 g",
      "isAvailable": true
    },
  ...
  ],
  "Polleo Sport": [...]
}
```
