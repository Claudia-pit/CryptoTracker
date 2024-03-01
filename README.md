CryptoTracker is the app for you if you are a crypto lover and want to keep track of all the changes during the week. 

This project runs on a min SDK of API 24 or Nougat/Android 7.0, you can both use an Android Studio phone simulator or a real smartphone.

The UI is totally made with Jetpack Compose and it follows MVVM pattern.

All of the crypto data are retrieved from Coingecko (https://www.coingecko.com/api/documentation) and implemented with Retrofit.

The chart in DetailSectionScreen was made with Vico library (https://patrykandpatrick.com/vico/wiki/).

--------

This is the Main Screen. We have a simple list of ten top crypto, each elements shows the logo of the coin, their name, symbol, current price
and price change percentage in the last 24h (red if it's negative, green if it's positive)


![MainScreen](https://github.com/Claudia-pit/CryptoTracker/assets/56250173/11e6c75d-c71f-4afe-9e3d-389a915c2113)



-------
This is the Detail Section. At the top I show the logo of the coin, their name, symbol, current price
and price change percentage in the last 24h (red if it's negative, green if it's positive), right above we have the chart that shows the sparkline about price changes 
for the last 7 days, then we have an overview of the coin with their description and a clickable text thats opens the official website.

![DetailSection](https://github.com/Claudia-pit/CryptoTracker/assets/56250173/ed7f68a5-094e-4a9f-bf6a-52e261321d28)


---------
If you don't have internet connection when launching the app, you'll see a simple error message

![ErrorDialog](https://github.com/Claudia-pit/CryptoTracker/assets/56250173/54305e2f-491f-444f-a4ba-07d3b867b40d)



