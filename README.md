# Telegram Update Viewer
TelegramUpdateViewer is service, that help you to analyze the dependency of telegramBotApi updates on telegram messages.
This app contains simple telegram bot and simple webSocket component. Telegram bot, being added to the chat, catch all messages (note, that you must set your bot's privacy parameter to disable). And the webSocket service transfer all catched updates to all connected via webSocket clients.

[![TelegramUpdateViewer](https://i.imgur.com/UEuonOT.png)](http://updateviewer.azzu.io/)

## HOW-TO

### Use already implemented one at azzu.io
Fastest way to use this app is add `@UpdateViewerBot` to your chat, and then examine all updates at [updateviewer.azzu.io](http://updateviewer.azzu.io/).

### Build your own
Before you will ready to boot app, you must perform several steps:
1. Register your own bot with the `@BotFather` by using `/newbot` command and get your bot's token by using `/token` command. (You can read more at [core.telegram.org](https://core.telegram.org/bots/api))
2. Create property named `application.yml` in resource folder and fill it with bot's parameters by using `example.properties.yml`.

After this steps you can boot the app.
1. Boot application by starting debug or run `mvn -package` and start `.war` file with Java.  
2. Open `resources/static/web/index.html` file in your browser.
3. Add your's bot to any chat and start messaging. 

All messages (telegram bot updates) will be displayed on the web page in json format.

---
> Especial thanks to `@yesmeck` for the porting Ben Hollis's JSONView extension for Firefox to [jQuery-plugin](http://github.com/yesmeck/jquery-jsonview) 