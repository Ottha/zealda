app.service('QuestService', ['$http', function QuestService($http) {

    QuestService.prototype.createQuest = function(questData) {
        console.log('calling api...');

        var questDataConverted = this.convertQuestData(questData);

        $http({ method: 'POST', url: '/quest', data: questDataConverted }).
            then(function(response) {
                console.log(response.data);
            }, function(response) {
                console.log('error');
            }
        );
    };

    QuestService.prototype.convertQuestData = function(questData) {
        return {
            name: questData.name,
            description: questData.description,
            expGain: questData.xp,
            levelRequired: 0,
            category: 'Test Category',
            repeatable: false,
            timeframeInMinutes: questData.duration
        }
    };

}]);
