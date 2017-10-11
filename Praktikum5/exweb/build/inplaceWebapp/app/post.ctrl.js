(function () {
    'use strict';

    angular.module('app').controller('PostCtrl', Ctrl);

    function Ctrl(http) {

        var vm = this;
        vm.insertNew = insertNew;
        vm.deletePost = deletePost;

        vm.newPost = {};
        vm.posts = [];

        init();

        function init() {
            http.get('api/posts').then(function (data) {
                vm.posts = data;
            });
        }

        function insertNew() {
            http.post('api/posts', vm.newPost).then(function () {
                vm.newPost = {};
                init();
            }, errorHandler);
        }

        function deletePost(postId) {
            http.delete('api/posts?id=' + postId).then(function () {
                init();
            }, errorHandler);
        }

        function errorHandler(response) {
            console.log('Error: ' + JSON.stringify(response.data));
        }
    }

})();
