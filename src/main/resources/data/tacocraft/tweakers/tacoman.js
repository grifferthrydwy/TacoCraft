var TraderTweaker = libcd.require("Tradesmen.TraderTweaker");

TraderTweaker.addTrader("tradesmen:tacoman", TraderTweaker.makeTrader()
    .name("Taco Jubilee")
    .animal("minecraft:bee")
    .texture("tradesmen:textures/entity/taco_man.png")
    .clothes("tradesmen:textures/entity/taco_man.png")
    .setTrades([[
            TraderTweaker.makeTrade("tacocraft:steak_taco@3",4,4,1),
            TraderTweaker.makeTrade("tacocraft:fish_taco@3",2,4,1),
            TraderTweaker.makeTrade("tacocraft:crunchy_taco@3",4,4,1),
            TraderTweaker.makeTrade("tacocraft:chicken_taco@3",2,4,1),
            TraderTweaker.makeTrade("tacocraft:cheesy_taco@3",3,4,1),
            TraderTweaker.makeTrade("tacocraft:carnitas_taco@3",3,4,1),
            TraderTweaker.makeTrade("tacocraft:al_pastor_taco@3",3,4,1),

            ],
        [
            TraderTweaker.makeTrade("tacocraft:tortilla@4",1,4,1),
            TraderTweaker.makeTrade("tacocraft:tortilla_dough@8",1,4,1),
            TraderTweaker.makeTrade("tacocraft:corn_seed@3",1,4,1),
            TraderTweaker.makeTrade("tacocraft:golden_taco@1",10,4,1),
        ]],[3,1]));