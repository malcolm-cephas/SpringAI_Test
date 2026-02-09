# Spring AI MCP Demo (Client & Server)

This project demonstrates the **Model Context Protocol (MCP)** using **Spring AI**. It consists of two Spring Boot applications: an MCP Server that exposes tools and an AI Client that consumes those tools to perform complex tasks.

## 🏗 Architecture

- **`mcp-server`**: Acts as an MCP Host. It provides tools (like querying a database or getting system time) that an AI can use.
- **`mcp-client`**: Acts as an MCP Client. It connects to the server, discovers available tools, and uses them within a chat conversation powered by an LLM (Groq/OpenAI/Gemini).

## 🚀 Features

- **MCP Server Tools**:
  - `getUserActivity`: Retrieves user summary data from an H2 database.
  - `getCurrentTime`: Provides the current system time in a specified format.
- **AI Client**:
  - Exposes a REST API: `POST /api/ai/chat`.
  - Automatically discovers and executes server-side tools via MCP.
  - Configured to use **Groq** (via OpenAI compatibility) by default.

## 🛠 Prerequisites

- Java 17 or higher
- Maven 3.6+
- An API Key for [Groq](https://console.groq.com/) (or OpenAI/Gemini)

## ⚙️ Configuration

### 1. API Keys
The client requires an API key. You can set it in `mcp-client/src/main/resources/application.properties` or as an environment variable:

```properties
# mcp-client/src/main/resources/application.properties
spring.ai.openai.api-key=${GROQ_API_KEY}
```

### 2. Server Port
- **Server**: Runs on port `8081` by default.
- **Client**: Runs on port `8080` by default.

## 🏃‍♂️ How to Run

### Step 1: Start the MCP Server
Navigate to the `mcp-server` directory and run:
```bash
mvn spring-boot:run
```

### Step 2: Start the AI Client
Navigate to the `mcp-client` directory and run:
```bash
mvn spring-boot:run
```

## 🧪 Usage

Once both services are running, you can interact with the AI using `curl` or any API client (like Postman).

**Endpoint**: `POST http://localhost:8080/api/ai/chat`

**Example Request**:
```json
{
  "message": "What is the current system time and can you check the activity for user 'malcolm' from 2024-01-01 to 2024-12-31?",
  "model": "groq"
}
```

The AI will:
1. Call `getCurrentTime` on the server.
2. Call `getUserActivity` on the server.
3. Combine the results into a human-readable response.

## 📝 Technologies Used

- **Spring Boot 3.4+**
- **Spring AI**: For LLM integration and MCP support.
- **Model Context Protocol (MCP)**: For standardized tool discovery.
- **H2 Database**: Used by the server for demo data.
- **Groq API**: High-performance LLM provider.

---
Developed by [Malcolm Cephas](https://github.com/malcolm-cephas)
