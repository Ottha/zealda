app.controller('QuestController', function QuestController(QuestService) {

    QuestController.prototype.questData = {
        name: '',
        description: '',
        xp: '',
        duration: ''
    };
  
    QuestController.prototype.submitQuest = function() {
        console.log('creating quest: ' + this.questData.name);
        QuestService.createQuest(this.questData);
    }

});