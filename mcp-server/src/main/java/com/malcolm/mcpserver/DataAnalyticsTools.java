package com.malcolm.mcpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class DataAnalyticsTools {
    private static final Logger logger = LoggerFactory.getLogger(DataAnalyticsTools.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Tool(description = "Get user activity summary from database")
    public UserActivitySummary getUserActivity(String userId, String startDate, String endDate) {
        logger.info("Getting user activity for: {}", userId);

        // Ensure the table exists for the example to work
        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS user_activity (user_id VARCHAR(255), score INT, created_date TIMESTAMP)");

        String sql = "SELECT COUNT(*) as total_actions, SUM(score) as total_score " +
                "FROM user_activity WHERE user_id = ? AND created_date BETWEEN ? AND ?";

        Map<String, Object> result = jdbcTemplate.queryForMap(sql, userId, startDate, endDate);

        return new UserActivitySummary(
                userId,
                ((Number) (result.get("total_actions") != null ? result.get("total_actions") : 0)).intValue(),
                ((Number) (result.get("total_score") != null ? result.get("total_score") : 0)).intValue());
    }

    @Tool(description = "Get current system time in specified format")
    public String getCurrentTime(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                format != null ? format : "yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    public static class UserActivitySummary {
        private String userId;
        private int totalActions;
        private int totalScore;

        public UserActivitySummary(String userId, int totalActions, int totalScore) {
            this.userId = userId;
            this.totalActions = totalActions;
            this.totalScore = totalScore;
        }

        public String getUserId() {
            return userId;
        }

        public int getTotalActions() {
            return totalActions;
        }

        public int getTotalScore() {
            return totalScore;
        }
    }
}
