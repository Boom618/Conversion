package com.caituo.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by boomhe on 2017/9/26.
 */
public class StringConversion extends AnAction {

    private long latestClickTime;

    public StringConversion() {
    }

    public StringConversion(Icon icon) {
        super(icon);
    }

    public StringConversion(@Nullable String text) {
        super(text);
    }

    public StringConversion(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    /**
     * 执行插件的入口，相当于java中的main方法
     *
     * @param anActionEvent
     */
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        if (!isFastClick(1000)) {
            Logger.init(getClass().getSimpleName(), Logger.DEBUG);
        }
        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);
        if (editor == null) {
            return;
        }
        SelectionModel model = editor.getSelectionModel();
        String text = model.getSelectedText();
        if (TextUtils.isEmpty(text)) {
            return;
        }
        Logger.debug(text);

        //下面这句话也可以使用 lambda 语法写：Runnable runnable = () -> editor.getDocument().replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(), TransitionUtils.spacingText(selectedText));
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //先对空格、制表符进行换掉，然后再重新进行分隔处理。其中分行不处理，因为在写文章的时候复制的一些内容分行是有意义的。
                editor.getDocument().replaceString(model.getSelectionStart(),
                        model.getSelectionEnd(),
                        TransitionUtils.spacingText(RegexExpressionUtils.replace(text, "\\f|\\r|\\t", "")));
            }
        };

        WriteCommandAction.runWriteCommandAction(anActionEvent.getData(PlatformDataKeys.PROJECT), runnable);
    }

    public boolean isFastClick(long timeMillis) {
        long time = System.currentTimeMillis();
        long timeD = time - latestClickTime;
        if (0 < timeD && timeD < timeMillis) {
            return true;
        }
        latestClickTime = time;
        return false;
    }
}
