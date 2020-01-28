package wanderingMiniBosses.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import wanderingMiniBosses.WanderingminibossesMod;

public class CustomSpawnMonsterAction extends SpawnMonsterAction {
    private AbstractMonster mon;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(WanderingminibossesMod.makeID("Announcement"));
    public static final String[] TEXT = uiStrings.TEXT;

    public CustomSpawnMonsterAction(AbstractMonster m) {
        super(m, false);
        mon = m;
    }

    @Override
    public void update() {
        super.update();
        if(isDone) {
            mon.usePreBattleAction();
            AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
                @Override
                public void update() {
                    mon.createIntent();
                    isDone = true;
                }
            });
            AbstractDungeon.actionManager.addToTop(new RollMoveAction(mon));
        }
    }
}
