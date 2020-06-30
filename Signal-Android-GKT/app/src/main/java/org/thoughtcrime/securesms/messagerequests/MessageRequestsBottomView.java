package org.thoughtcrime.securesms.messagerequests;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.text.HtmlCompat;

import org.thoughtcrime.securesms.R;
import org.thoughtcrime.securesms.recipients.Recipient;
import org.thoughtcrime.securesms.util.HtmlUtil;

public class MessageRequestsBottomView extends ConstraintLayout {

  private TextView question;
  private View     accept;
  private View     block;
  private View     delete;
  private View     bigDelete;
  private View     bigUnblock;

  private Group normalButtons;
  private Group blockedButtons;

  public MessageRequestsBottomView(Context context) {
    super(context);
  }

  public MessageRequestsBottomView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MessageRequestsBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    inflate(getContext(), R.layout.message_request_bottom_bar, this);

    question       = findViewById(R.id.message_request_question);
    accept         = findViewById(R.id.message_request_accept);
    block          = findViewById(R.id.message_request_block);
    delete         = findViewById(R.id.message_request_delete);
    bigDelete      = findViewById(R.id.message_request_big_delete);
    bigUnblock     = findViewById(R.id.message_request_big_unblock);
    normalButtons  = findViewById(R.id.message_request_normal_buttons);
    blockedButtons = findViewById(R.id.message_request_blocked_buttons);
  }

  public void setRecipient(@NonNull Recipient recipient) {
    if (recipient.isBlocked()) {
      if (recipient.isGroup()) {
        question.setText(R.string.MessageRequestBottomView_unblock_this_group_and_share_your_name_and_photo_with_its_members);
      } else {
        String name = recipient.getProfileName().isEmpty() ? recipient.getDisplayName(getContext()) : recipient.getProfileName().getGivenName();
        question.setText(HtmlCompat.fromHtml(getContext().getString(R.string.MessageRequestBottomView_do_you_want_to_let_s_message_you_wont_receive_any_messages_until_you_unblock_them, HtmlUtil.bold(name)), 0));
      }
      normalButtons.setVisibility(GONE);
      blockedButtons.setVisibility(VISIBLE);
    } else {
      if (recipient.isGroup()) {
        if (recipient.isPushV2Group()) {
          question.setText(HtmlCompat.fromHtml(getContext().getString(R.string.MessageRequestBottomView_you_were_invited_to_join_the_group_s, HtmlUtil.bold(recipient.getDisplayName(getContext()))), 0));
        } else {
          question.setText(R.string.MessageRequestBottomView_do_you_want_to_join_this_group_they_wont_know_youve_seen_their_messages_until_you_accept);
        }
      } else {
        String name = recipient.getProfileName().isEmpty() ? recipient.getDisplayName(getContext()) : recipient.getProfileName().getGivenName();
        question.setText(HtmlCompat.fromHtml(getContext().getString(R.string.MessageRequestBottomView_do_you_want_to_let_s_message_you_they_wont_know_youve_seen_their_messages_until_you_accept, HtmlUtil.bold(name)), 0));
      }
      normalButtons.setVisibility(VISIBLE);
      blockedButtons.setVisibility(GONE);
    }
  }

  public void setAcceptOnClickListener(OnClickListener acceptOnClickListener) {
    accept.setOnClickListener(acceptOnClickListener);
  }

  public void setDeleteOnClickListener(OnClickListener deleteOnClickListener) {
    delete.setOnClickListener(deleteOnClickListener);
    bigDelete.setOnClickListener(deleteOnClickListener);
  }

  public void setBlockOnClickListener(OnClickListener blockOnClickListener) {
    block.setOnClickListener(blockOnClickListener);
  }

  public void setUnblockOnClickListener(OnClickListener unblockOnClickListener) {
    bigUnblock.setOnClickListener(unblockOnClickListener);
  }
}
