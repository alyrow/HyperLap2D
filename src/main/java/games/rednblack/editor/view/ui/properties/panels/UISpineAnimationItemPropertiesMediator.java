/*
 * ******************************************************************************
 *  * Copyright 2015 See AUTHORS file.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *****************************************************************************
 */

package games.rednblack.editor.view.ui.properties.panels;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.Animation;
import games.rednblack.editor.controller.commands.component.UpdateSpineDataCommand;
import games.rednblack.editor.renderer.data.SpineVO;
import games.rednblack.h2d.common.MsgAPI;
import games.rednblack.h2d.extention.spine.SpineObjectComponent;
import games.rednblack.editor.view.ui.properties.UIItemPropertiesMediator;
import games.rednblack.editor.renderer.components.SpineDataComponent;
import games.rednblack.editor.renderer.utils.ComponentRetriever;

public class UISpineAnimationItemPropertiesMediator extends UIItemPropertiesMediator<Entity, UISpineAnimationItemProperties> {
    private static final String TAG = UISpineAnimationItemPropertiesMediator.class.getCanonicalName();
    public static final String NAME = TAG;

    private SpineDataComponent spineDataComponent;
    private SpineObjectComponent spineObjectComponent;

    public UISpineAnimationItemPropertiesMediator() {
        super(NAME, new UISpineAnimationItemProperties());
    }

    @Override
    protected void translateObservableDataToView(Entity entity) {
        spineObjectComponent = ComponentRetriever.get(entity, SpineObjectComponent.class);
        spineDataComponent = ComponentRetriever.get(entity, SpineDataComponent.class);
    	
        Array<String> animations = new Array<>();
        for (Animation animation : spineObjectComponent.getAnimations()) {
            animations.add(animation.getName());
        }

        viewComponent.setAnimations(animations);
        viewComponent.setSelectedAnimation(spineDataComponent.currentAnimationName);
    }

    @Override
    protected void translateViewToItemData() {
        SpineVO payloadVO = new SpineVO();
        payloadVO.currentAnimationName = viewComponent.getSelected();

        Object payload = UpdateSpineDataCommand.payload(observableReference, payloadVO);
        facade.sendNotification(MsgAPI.ACTION_UPDATE_SPINE_ANIMATION_DATA, payload);
    }
}
