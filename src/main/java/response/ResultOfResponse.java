package response;

/**
 * Links a command result message and whether the message is an
 * error message or not an error.
 */
public class ResultOfResponse {

        private boolean isError;
        private String message;

        /**
         * Constructs a response result which contains a boolean and a string.
         * @param isError whether the command message is an error or not an error.
         * @param message command result message
         */
        public ResultOfResponse(boolean isError, String message) {
            this.isError = isError;
            this.message = message;
        }

        public boolean isError() {
            return isError;
        }

        public String getMessage() {
            return message;
        }
}
