(function () {
    'use strict';

    var messages = {
        'NotNull.post.title' : 'Post title is missing',
        'NotNull.post.text' : 'Post text is missing',
        'Size.post.title' : 'Title must be between {2} and {1} characters',
        'Size.post.text' : 'Text must be between {2} and {1} characters'
    };

    angular.module('app').service('messageService', Srv);

    function Srv() {

        this.getMessage = getMessage;

        function getMessage(key, params) {
            var text = messages[key];

            if (text === undefined) {
                return "Unknown message key: " + key;
            }

            if (params === undefined) {
                return text;
            }

            for (var i = 0; i < params.length; i++) {
                text = text.replace(new RegExp('\\{' + (i + 1) + '\\}', 'g'), params[i]);
            }

            return text;
        }

    }

})();
