define([],function(){})
/*
define([
    'qwery',
    'lodash/objects/assign',
    'common/modules/onward/popular',
    'common/modules/onward/social-burners',
    'common/utils/context'
], function(

    qwery,
    extend,
    Popular,
    SocialBurners,
    context
    ) {

    function MostPopularFactory(config) {

        this.config = config;

        this.context = context();
        this.renderComponent();
    }

    MostPopularFactory.showReferredContent = false;

    MostPopularFactory.setShowReferred = function() {
        MostPopularFactory.showReferredContent = true;
    };

    MostPopularFactory.prototype.renderComponent = function() {
        if(MostPopularFactory.showReferredContent) {
           var s = new SocialBurners(this.config, qwery('.js-onward', this.context));
           return s;
        } else {
           var p = new Popular(this.config, this.context);
           return p;
        }
    };

    return MostPopularFactory;

});
*/