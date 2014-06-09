define([
    'common/modules/onward/history',
    'common/utils/mediator',
    'common/modules/onward/most-popular-factory'
], function (
    History,
    Mediator,
    MostPopularFactory
    ) {

    var DisplayReferredContent = function() {

        this.id = 'DisplayReferredContent';
        this.start = '2014=06-03';
        this.expiry = '2014-06-17';
        this.description = 'Will display content referred from social media to users who have visited less than 10 times in the previous month';
        this.audience = '0.4';
        this.audienceOffset = '0.6';

        this.canRun = function() {
            console.log('++ Is this running');
            return true;
        };

        this.variants = [
            {
                id: 'control',
                test: function() {
                   console.log("++++ Control");
                }
            },
            {
                id: 'show-referred-content',
                test: function() {
                    console.log("We are running this mother");
                    var date = new Date();
                    date.setMonth(date.getMonth()-1);
                    if( new History().hasVisitedInPeriodSince(date, 10)) {
                      // MostPopularFactory.setShowReferred();
                    }
                }
            }
        ];
    };

    return DisplayReferredContent;

});