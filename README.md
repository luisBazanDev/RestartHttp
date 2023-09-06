# RestartHttp
It is a minecraft plugin to restart the server, through GET requests to the server. It was made for a better development environment for the CS16MC plugin.

### Config
```yaml
port: 8090
```

### Routes
- `/restart` - Execute restart command on the server
- `/reload` - Call the server's reload method
- `/*` - Version of the plugin in JSON format