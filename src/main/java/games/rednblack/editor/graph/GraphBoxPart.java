package games.rednblack.editor.graph;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import games.rednblack.editor.graph.data.FieldType;
import org.json.simple.JSONObject;

public interface GraphBoxPart<T extends FieldType> extends Disposable {
    Actor getActor();

    GraphBoxOutputConnector<T> getOutputConnector();

    GraphBoxInputConnector<T> getInputConnector();

    void serializePart(JSONObject object);
}
