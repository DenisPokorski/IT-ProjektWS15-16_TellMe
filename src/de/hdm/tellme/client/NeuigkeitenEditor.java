package de.hdm.tellme.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.TreeViewModel;
import com.sun.java.swing.plaf.windows.resources.windows;

import de.hdm.tellme.client.gui.editor.HashtagZelle;
import de.hdm.tellme.client.gui.editor.NeuigkeitenEinzelNachricht;
import de.hdm.tellme.client.gui.editor.NeuigkeitenNachrichtenBaumModel;
import de.hdm.tellme.client.gui.editor.NeuigkeitenNachrichtDialogbox;
import de.hdm.tellme.client.gui.editor.NutzerZelle;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Unterhaltung;

public class NeuigkeitenEditor extends VerticalPanel {

	public NeuigkeitenEditor() {
	}

	public void FilterNachBenutzer(NutzerZelle.ZellenObjekt nah) {
		Window.alert("Nachrichten werden nach Benutzer " + nah.nutzer.getVorname());
	}

	public void FilterNachHashtag(HashtagZelle.ZellenObjekt nah) {
		Window.alert("Nachrichten werden nach Benutzer " + nah.hashtag.getSchlagwort());
	}

	public void onLoad() {

		HorizontalPanel hpOptionen = new HorizontalPanel();
		hpOptionen.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		Button btnNeueNachricht = new Button("Neue Nachricht verfassen");
		btnNeueNachricht.addClickHandler(btnNeueNachrichtClickHandler);
		hpOptionen.add(btnNeueNachricht);

		RootPanel.get("content_right").add(hpOptionen);

		TreeViewModel model = new NeuigkeitenNachrichtenBaumModel();
		/*
		 * Create the tree using the model. We use <code>null</code> as the
		 * default value of the root node. The default value will be passed to
		 * CustomTreeModel#getNodeInfo();
		 */
		CellTree tree = new CellTree(model, null);
		tree.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		RootPanel.get("content_right").add(tree);

		Button btnTest = new Button("Test");
		RootPanel.get("content_right").add(btnTest);
			

	}

	ClickHandler btnNeueNachrichtClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenNachrichtDialogbox().getNeueNachrichtDialogbox());
		}
	};

	public static void showDialogBox(DialogBox anzuzeigendeBox) {
		anzuzeigendeBox.center();
		anzuzeigendeBox.show();
	}

}
