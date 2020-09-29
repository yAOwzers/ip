package response;

/**
 * Links a command result message and whether the message is an
 * error message or not
 */
public class ResponseResult {

        private boolean isError;
        private String message;

        /**
         * Constructs a response result which contains a boolean and a string.
         * @param isError whether the command message is an error or not.
         * @param message command result message
         */
        public ResponseResult(boolean isError, String message) {
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
