package games.rednblack.editor.view.ui.properties;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisTable;
import games.rednblack.h2d.common.view.ui.StandardWidgetsFactory;
import games.rednblack.editor.view.stage.Sandbox;

/**
 * Created by CyberJoe on 7/2/2015.
 */
public abstract class UIRemovableProperties extends UIItemCollapsibleProperties {

    public UIRemovableProperties(String title) {
        super(title);
    }

    @Override
    public Table crateHeaderTable() {
        VisTable header = new VisTable();
        header.setBackground(VisUI.getSkin().getDrawable("expandable-properties-active-bg"));
        VisImageButton collapseButton = StandardWidgetsFactory.createImageButton("expandable-properties-button");
        VisImageButton closeButton = StandardWidgetsFactory.createImageButton("close-properties");
        header.add(closeButton).left().padLeft(2);
        header.add(StandardWidgetsFactory.createLabel(title)).left().expandX().padLeft(6);
        header.add(collapseButton).right().padRight(8);
        header.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                collapsibleWidget.setCollapsed(!collapsibleWidget.isCollapsed());
                header.setBackground(VisUI.getSkin().getDrawable("expandable-properties-" + (collapsibleWidget.isCollapsed() ? "inactive" : "active") + "-bg"));
            }
        });
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Dialogs.showConfirmDialog(Sandbox.getInstance().getUIStage(),
                        "Delete Component", "Do you want to delete this component?",
                        new String[]{"Cancel", "Delete"}, new Integer[]{0, 1}, r -> {
                            if (r == 1) {
                                onRemove();
                                remove();
                            }
                        }).padBottom(20).pack();
            }
        });
        return header;
    }

    public abstract void onRemove();
}
