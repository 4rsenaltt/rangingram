package com.atsoft.telegram.kui;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import org.telegram.messenger.R;

/**
 * Created by future on 12/2/2015.
 */
public class KharTheme
{
    public int main = 0xffff5500;
    public int main_dark = 0x90ff5500;
    public int main_light = 0xff800F05;

    private int getMain(){return main;}

   public void updateMain(int newMain){
       main = newMain;
       DrawerProfileCell_bg_color = main;
       HeaderCell_textView_color = main;
       TextSettingsCell_valueTextView_color = main;
   }

    public KharTheme()
    {

    }


    //ResourceLoader
    public  Drawable ResourceLoader_backgroundDrawableIn;
    public  Drawable ResourceLoader_backgroundDrawableInSelected;
    public  Drawable ResourceLoader_backgroundDrawableOut;
    public  Drawable ResourceLoader_backgroundDrawableOutSelected;
    public  Drawable ResourceLoader_backgroundMediaDrawableIn;
    public  Drawable ResourceLoader_backgroundMediaDrawableInSelected;
    public  Drawable ResourceLoader_backgroundMediaDrawableOut;
    public  Drawable ResourceLoader_backgroundMediaDrawableOutSelected;
    public  Drawable ResourceLoader_checkDrawable;
    public  Drawable ResourceLoader_halfCheckDrawable;
    public  Drawable ResourceLoader_clockDrawable;
    public  Drawable ResourceLoader_broadcastDrawable;
    public  Drawable ResourceLoader_checkMediaDrawable;
    public  Drawable ResourceLoader_halfCheckMediaDrawable;
    public  Drawable ResourceLoader_clockMediaDrawable;
    public  Drawable ResourceLoader_broadcastMediaDrawable;
    public  Drawable ResourceLoader_errorDrawable;
    public  Drawable ResourceLoader_backgroundBlack;
    public  Drawable ResourceLoader_backgroundBlue;
    public  Drawable ResourceLoader_mediaBackgroundDrawable;
    public  Drawable ResourceLoader_clockChannelDrawable;

    public  Drawable ResourceLoader_viewsCountDrawable;
    public  Drawable ResourceLoader_viewsOutCountDrawable;
    public  Drawable ResourceLoader_viewsMediaCountDrawable;

    public  Drawable ResourceLoader_geoInDrawable;
    public  Drawable ResourceLoader_geoOutDrawable;

    public  Drawable[][] ResourceLoader_audioStatesDrawable = new Drawable[10][2];

    public  Drawable ResourceLoader_placeholderDocInDrawable;
    public  Drawable ResourceLoader_placeholderDocOutDrawable;
    public  Drawable ResourceLoader_videoIconDrawable;
    public  Drawable ResourceLoader_docMenuInDrawable;
    public  Drawable ResourceLoader_docMenuOutDrawable;
    public  Drawable[] ResourceLoader_buttonStatesDrawables = new Drawable[8];
    public  Drawable[][] ResourceLoader_buttonStatesDrawablesDoc = new Drawable[3][2];









    public Drawable list_selector_ktt;
    public Drawable getList_selector()
    {
        StateListDrawable stateListDrawable = (StateListDrawable) list_selector_ktt;
      return   stateListDrawable.getConstantState().newDrawable().mutate();
    }



    //cells

        //AddMemberCell
    public int AddMemberCell_color = 0xff212121;

        //AudioCell
    public int AudioCell_paint_color = 0xffd9d9d9;
    public int AudioCell_playButton_play_R = R.drawable.audiosend_play;
    public int AudioCell_playButton_pause_R = R.drawable.audiosend_pause;
    public int AudioCell_titleTextView_color = 0xff212121;
    public int AudioCell_genreTextView_color = 0xff8a8a8a;
    public int AudioCell_authorTextView_color = 0xff8a8a8a;
    public int AudioCell_timeTextView_color = 0xff999999;
    public int AudioCell_checkBox_color = 0xff29b6f7;



        //BotHelpCell
    public int BotHelpCell_textPaint_color = 0xff000000;
    public int BotHelpCell_textPaint_linkColor = 0xff316f9f;
    public int BotHelpCell_urlPaint_color = 0x33316f9f;



        //ChatActionCell
    public int ChatActionCell_textPaint_color = 0xffffffff;
    public int ChatActionCell_textPaint_linkColor = 0xffffffff;


        //ChatAudioCell
    public int ChatAudioCell_timePaint_color_1 = 0xff70b15c;
    public int ChatAudioCell_circlePaint_color_1 = 0xff87bf78;
    public int ChatAudioCell_timePaint_color_2 = 0xffa1aab3;
    public int ChatAudioCell_circlePaint_color_2 = 0xff4195e5;



        //ChatBaseCell
    public int ChatBaseCell_timePaintIn_color = 0xffa1aab3;
    public int ChatBaseCell_timePaintOut_color = 0xff70b15c;
    public int ChatBaseCell_timeMediaPaint_color = 0xffffffff;
    public int ChatBaseCell_replyTextPaint_linkColor = 0xff316f9f;
    public int ChatBaseCell_urlPaint_color = 0x33316f9f;
    public int ChatBaseCell_forwardNamePaint_color_1 = 0xff4a923c;
    public int ChatBaseCell_forwardNamePaint_color_2 = 0xff006fc8;
    public int ChatBaseCell_replyLinePaint_color = 0xffffffff;
    public int ChatBaseCell_replyNamePaint_color = 0xffffffff;
    public int ChatBaseCell_replyTextPaint_color = 0xffffffff;
    public int ChatBaseCell_replyLinePaint_color_2 = 0xff8dc97a;
    public int ChatBaseCell_replyNamePaint_color_2 = 0xff61a349;
    public int ChatBaseCell_replyTextPaint_color_2 = 0xff000000;
    public int ChatBaseCell_replyTextPaint_color_3 = 0xff70b15c;
    public int ChatBaseCell_replyLinePaint_color_3 = 0xff6c9fd2;
    public int ChatBaseCell_replyNamePaint_color_3 = 0xff377aae;
    public int ChatBaseCell_replyTextPaint_color_4 = 0xff000000;
    public int ChatBaseCell_replyTextPaint_color_5 = 0xff999999;




    //ChatContactCell
    public int ChatContactCell_phonePaint_color = 0xff212121;
    public int ChatContactCell_addContactDrawableIn_R = R.drawable.addcontact_blue;
    public int ChatContactCell_addContactDrawableOut_R = R.drawable.addcontact_green;


        //ChatLoadingCell
    public int ChatLoadingCell_frameLayout_bg1_R = R.drawable.system_loader2;
    public int ChatLoadingCell_frameLayout_bg2_R = R.drawable.system_loader1;
    public int ChatLoadingCell_progressBar_indeterminateDrawable_R = R.drawable.loading_animation;




        //ChatMediaCell
    public int ChatMediaCell_deleteProgressPaint_color = 0xffe4e2e0;
    public int ChatMediaCell_infoPaint_color = 0xff70b15c;
    public int ChatMediaCell_docBackPaint_color = 0xffdaf5c3;
    public int ChatMediaCell_infoPaint_color_2 = 0xffa1adbb;
    public int ChatMediaCell_docBackPaint_color_2 = 0xffebf0f5;
    public int ChatMediaCell_radialProgress_color = 0xff81bd72;
    public int ChatMediaCell_radialProgress_color_2 = 0xffadbdcc;
    public int ChatMediaCell_radialProgress_color_3 = 0xffffffff;
    public int ChatMediaCell_radialProgress_color_4 = 0xffffffff;
    public int ChatMediaCell_infoPaint_color_3 = 0xffffffff;
    public int ChatMediaCell_locationAddressPaint_color_1 = 0xff70b15c;
    public int ChatMediaCell_locationAddressPaint_color_2 = 0xff999999;




        //ChatMessageCell
    public int ChatMessageCell_durationPaint_color = 0xffffffff;
    public int ChatMessageCell_replyLinePaint_color = 0xff8dc97a;
    public int ChatMessageCell_replyLinePaint_color_2 = 0xff6c9fd2;
    public int ChatMessageCell_replyNamePaint_color = 0xff70b15c;
    public int ChatMessageCell_replyNamePaint_color_2 = 0xff4b91cf;
    public int ChatMessageCell_replyNamePaint_color_3 = 0xff000000;
    public int ChatMessageCell_replyNamePaint_color_4 = 0xff000000;
    public int ChatMessageCell_replyTextPaint_color   = 0xff000000;
    public int ChatMessageCell_igvideo_bg_R = R.drawable.igvideo;



        //ChatMusicCell
    public int ChatMusicCell_titlePaint_color = 0xff212121;
    public int ChatMusicCell_authorPaint_color = 0xff212121;
    public int ChatMusicCell_radialProgress_color = 0xff87bf78;
    public int ChatMusicCell_radialProgress_color_2 = 0xffa2b5c7;
    public int ChatMusicCell_timePaint_color = 0xff70b15c;
    public int ChatMusicCell_timePaint_color_2 = 0xffa1aab3;




        //ChatUnreadCell
    public int ChatUnreadCell_frameLayout_bg_R = R.drawable.newmsg_divider;
    public int ChatUnreadCell_imageView_img_R = R.drawable.ic_ab_new;
    public int ChatUnreadCell_textView_color = 0xff4a7297;



        //DialogCell
    public int DialogCell_namePaint_color = 0xff212121;
    public int DialogCell_nameEncryptedPaint_color = 0xff00a60e;
    public int DialogCell_nameUnknownPaint_color = 0xff4d83b3;
    public int DialogCell_messagePaint_color = 0xff8f8f8f;
    public int DialogCell_messagePaint_linkColor = 0xff8f8f8f;
    public int DialogCell_linePaint_color = 0xffdcdcdc;
    public int DialogCell_backPaint_color = 0x0f000000;
    public int DialogCell_messagePrintingPaint = 0xff4d83b3;
    public int DialogCell_timePaint_color = 0xff999999;
    public int DialogCell_countPaint_color = 0xffffffff;
    public int DialogCell_lockDrawable_R = R.drawable.list_secret;
    public int DialogCell_checkDrawable_R = R.drawable.dialogs_check;
    public int DialogCell_halfCheckDrawable_R = R.drawable.dialogs_halfcheck;
    public Drawable DialogCell_checkDrawable;
    public Drawable DialogCell_halfCheckDrawable;
    public int DialogCell_clockDrawable_R = R.drawable.msg_clock;
    public int DialogCell_errorDrawable_R = R.drawable.dialogs_warning;
    public int DialogCell_countDrawable_R = R.drawable.dialogs_badge;
    public int DialogCell_countDrawableGrey_R = R.drawable.dialogs_badge2;
    public int DialogCell_groupDrawable_R = R.drawable.list_group;
    public int DialogCell_broadcastDrawable_R = R.drawable.list_broadcast;
    public int DialogCell_muteDrawable_R = R.drawable.mute_grey;
    public int DialogCell_verifiedDrawable_R = R.drawable.check_list;
    ///public int DialogCell_bg_R = R.drawable.list_selector;




        //DividerCell
    public int DividerCell_paint_color = 0xffd9d9d9;




        //DrawerActionCell
    public int DrawerActionCell_textView_color = 0xff444444;



        //DrawerProfileCell
    public int DrawerProfileCell_bg_color = main;
    public int DrawerProfileCell_nameTextView_color = 0xffffffff;
    public int DrawerProfileCell_phoneTextView_color = 0xffc2e5ff;
    public int DrawerProfileCell_phoneTextView_color_2 = 0xffffffff;
    public int DrawerProfileCell_phoneTextView_color_3 = 0xffc2e5ff;
    public int DrawerProfileCell_avatarDrawable_color = 0xff5c98cd;




        //GreySectionCell
    public int GreySectionCell_bg_color = 0xfff2f2f2;
    public int GreySectionCell_textView_color = 0xff8a8a8a;



        //HashtagSearchCell
    public int HashtagSearchCell_text_color = 0xff000000;
    public int HashtagSearchCell_paint_color = 0xffdcdcdc;
    ///public int HashtagSearchCell_bg_R = R.drawable.list_selector;



        //HeaderCell
    public int HeaderCell_textView_color = main;



        //LastSeenRadioCell
    public int LastSeenRadioCell_paint_color = 0xffd9d9d9;
    public int LastSeenRadioCell_textView_color = 0xff212121;
    public int LastSeenRadioCell_radioButton_color_1 = 0xffb3b3b3;
    public int LastSeenRadioCell_radioButton_color_2 = 0xff37a9f0;




        //LetterSectionCell
    public int LetterSectionCell_textView_color = 0xff808080;




        //LocationCell
    public int LocationCell_paint_color = 0xffd9d9d9;
    public int LocationCell_PorterDuffColorFilter_color = 0xff999999;
    public int LocationCell_nameTextView_color = 0xff212121;
    public int LocationCell_addressTextView_color = 0xff999999;




        //LocationLoadingCell
    public int LocationLoadingCell_textView_color = 0xff999999;





        //LocationPoweredCell
    public int LocationPoweredCell_textView_color = 0xff999999;
    public int LocationPoweredCell_imageView_img = R.drawable.foursquare;
    public int LocationPoweredCell_textView_color2 = 0xff999999;




        //MentionCell
    public int MentionCell_nameTextView_color = 0xff000000;
    public int MentionCell_usernameTextView_color = 0xff999999;
    public int MentionCell_nameTextView_color_2 = 0xffffffff;
    public int MentionCell_usernameTextView_color_2 = 0xff999999;
    public int MentionCell_nameTextView_color_3 = 0xff000000;
    public int MentionCell_usernameTextView_color_3 = 0xff999999;





        //PhotoAttachCameraCell
    public int PhotoAttachCameraCell_imageView_bg = 0xff777777;





        //PhotoAttachPhotoCell
    public int PhotoAttachPhotoCell_checkBox_R = R.drawable.checkbig;
    public int PhotoAttachPhotoCell_checkBox_color = 0xff3ccaef;
    public int PhotoAttachPhotoCell_imageView_1_R = R.drawable.nophotos;





        //PhotoEditToolCell
    public int PhotoEditToolCell_nameTextView_color = 0xffffffff;
    public int PhotoEditToolCell_valueTextView_color = 0xff6cc3ff;








        //PhotoPickerAlbumsCell
    public int PhotoPickerAlbumsCell_linearLayout_color = 0x7f000000;
    public int PhotoPickerAlbumsCell_nameTextView_color = 0xffffffff;
    public int PhotoPickerAlbumsCell_countTextView_color = 0xffaaaaaa;
    public int PhotoPickerAlbumsCell_imageView_img_R = R.drawable.nophotos;
    ///public int PhotoPickerAlbumsCell_selector_bg_R = R.drawable.list_selector;




        //PhotoPickerPhotoCell
    public int PhotoPickerPhotoCell_checkBox__drawable_checkbig_R = R.drawable.checkbig;
    public int PhotoPickerPhotoCell_checkBox_color = 0xff3ccaef;
    public int PhotoPickerPhotoCell_bg_color = 0xff0A0A0A;




        //PhotoPickerSearchCell
    public int PhotoPickerSearchCell_bg_color = 0xff1a1a1a;
    ///public int PhotoPickerSearchCell_selector_bg_R = R.drawable.list_selector;
    public int PhotoPickerSearchCell_textView1_color = 0xffffffff;
    public int PhotoPickerSearchCell_textView2_color = 0xff666666;



        //ProfileSearchCell
    public int ProfileSearchCell_namePaint_color = 0xff212121;
    public int ProfileSearchCell_nameEncryptedPaint_color = 0xff00a60e;
    public int ProfileSearchCell_onlinePaint_color = 0xff316f9f;
    public int ProfileSearchCell_offlinePaint_color = 0xff999999;
    public int ProfileSearchCell_linePaint_color = 0xffdcdcdc;
    public int ProfileSearchCell_countPaint_color = 0xffffffff;
    public int ProfileSearchCell_broadcastDrawable_R = R.drawable.list_broadcast;
    public int ProfileSearchCell_lockDrawable_R = R.drawable.list_secret;
    public int ProfileSearchCell_groupDrawable_R = R.drawable.list_group;
    public int ProfileSearchCell_countDrawable_R = R.drawable.dialogs_badge;
    public int ProfileSearchCell_checkDrawable_R = R.drawable.check_list;





        //RadioButtonCell
    public int RadioButtonCell_paint_color = 0xffd9d9d9;
    public int RadioButtonCell_radioButton_color_1 = 0xffb3b3b3;
    public int RadioButtonCell_radioButton_color_2 = 0xff37a9f0;
    public int RadioButtonCell_textView_color = 0xff212121;
    public int RadioButtonCell_valueTextView_color = 0xff8a8a8a;





        //SendLocationCell
    public int SendLocationCell_titleTextView_color = 0xff377aae;
    public int SendLocationCell_imageView_img_R = R.drawable.pin;
    public int SendLocationCell_accurateTextView_color = 0xff999999;





        //SessionCell
    public int SessionCell_paint_color = 0xffd9d9d9;
    public int SessionCell_nameTextView_color = 0xff212121;
    public int SessionCell_detailTextView_color = 0xff212121;
    public int SessionCell_detailExTextView_color = 0xff999999;
    public int SessionCell_onlineTextView_color = 0xff2f8cc9;
    public int SessionCell_onlineTextView_color_2 = 0xff999999;




        //ShadowBottomSectionCell
    public int ShadowBottomSectionCell_bg_R = R.drawable.greydivider_bottom;



        //ShadowSectionCell
    public int ShadowSectionCell_bg_R = R.drawable.greydivider;



        //SharedDocumentCell
    public int SharedDocumentCell_icons_1_R = R.drawable.media_doc_blue;
    public int SharedDocumentCell_icons_2_R = R.drawable.media_doc_green;
    public int SharedDocumentCell_icons_3_R = R.drawable.media_doc_red;
    public int SharedDocumentCell_icons_4_R = R.drawable.media_doc_yellow;
    public int SharedDocumentCell_paint_color = 0xffd9d9d9;
    public int SharedDocumentCell_extTextView_color = 0xffffffff;
    public int SharedDocumentCell_nameTextView_color = 0xff212121;
    public int SharedDocumentCell_dateTextView_color = 0xff999999;
    public int SharedDocumentCell_statusImageView_img_1_R = R.drawable.media_doc_pause;
    public int SharedDocumentCell_statusImageView_img_2_R = R.drawable.media_doc_load;





        //SharedLinkCell
    public int SharedLinkCell_titleTextPaint_color = 0xff212121;
    public int SharedLinkCell_paint_color = 0xffd9d9d9;
    public int SharedLinkCell_urlPaint_color = 0x33316f9f;
    public int SharedLinkCell_checkBox_R = R.drawable.round_check2;
    public int SharedLinkCell_descriptionTextPaint_color = 0xff212121;
    public int SharedLinkCell_descriptionTextPaint_color_2 = 0xff212121;
    public int SharedLinkCell_descriptionTextPaint_color_3 = 0xff316f9f;




        //SharedMediaSectionCell
    public int SharedMediaSectionCell_textView_color = 0xff212121;




        //SharedPhotoVideoCell
    public int SharedPhotoVideoCell_videoInfoContainer_bg_R = R.drawable.phototime;
    public int SharedPhotoVideoCell_imageView1_img_R = R.drawable.ic_video;
    public int SharedPhotoVideoCell_videoTextView_color = 0xffffffff;
    ///public int SharedPhotoVideoCell_selector_R = R.drawable.list_selector;
    public int SharedPhotoVideoCell_checkBox_R = R.drawable.round_check2;
    public int SharedPhotoVideoCell_bg_color = 0xfff5f5f5;
    public int SharedPhotoVideoCell_bg_color_2 = 0xfff5f5f5;
    public int SharedPhotoVideoCell_photoVideoView_img_R = R.drawable.photo_placeholder_in;
    public int SharedPhotoVideoCell_photoVideoView_img_2_R = R.drawable.photo_placeholder_in;
    public int SharedPhotoVideoCell_photoVideoView_img_3_R = R.drawable.photo_placeholder_in;
    public int SharedPhotoVideoCell_photoVideoView_img_4_R = R.drawable.photo_placeholder_in;




        //StickerCell
    public int StickerCell_bg_1_R = R.drawable.stickers_back_left;
    public int StickerCell_bg_2_R = R.drawable.stickers_back_center;
    public int StickerCell_bg_3_R = R.drawable.stickers_back_right;
    public int StickerCell_bg_4_R = R.drawable.stickers_back_all;





        //StickerSetCell
    public int StickerSetCell_paint_color = 0xffd9d9d9;
    public int StickerSetCell_textView_color = 0xff212121;
    public int StickerSetCell_valueTextView_color = 0xff8a8a8a;
    public int StickerSetCell_optionsButton_R = R.drawable.bar_selector_grey;




        //TextBlockCell
    public int TextBlockCell_v_color = 0xffd9d9d9;
    public int TextBlockCell_textView_color = 0xff212121;




        //TextCell
    public int TextCell_textView_color = 0xff212121;
    public int TextCell_valueTextView_color = 0xff2f8cc9;



        //TextCheckCell
    public int TextCheckCell_paint_color = 0xffd9d9d9;
    public int TextCheckCell_textView_color = 0xff212121;




        //TextColorCell
    public int TextColorCell_paint_color = 0xffd9d9d9;
    public int TextColorCell_colorDrawable_R = R.drawable.switch_to_on2;
    public int TextColorCell_textView_color = 0xff212121;




        //TextDetailCell
    public int TextDetailCell_textView_color = 0xff212121;
    public int TextDetailCell_valueTextView_color = 0xff8a8a8a;



        //TextDetailSettingsCell
    public int TextDetailSettingsCell_paint_color = 0xffd9d9d9;
    public int TextDetailSettingsCell_textView_color = 0xff212121;
    public int TextDetailSettingsCell_valueTextView_color = 0xff8a8a8a;




        //TextInfoCell
    public int TextInfoCell_textView_color = 0xffa3a3a3;





        //TextInfoPrivacyCell
    public int TextInfoPrivacyCell_textView_color = 0xff808080;
    public int TextInfoPrivacyCell_textView_link_color = 0xff316f9f;




        //TextSettingsCell
    public int TextSettingsCell_paint_color = 0xffd9d9d9;
    public int TextSettingsCell_textView_color = 0xff212121;
    public int TextSettingsCell_valueTextView_color = main;





        //UserCell
    public int UserCell_statusColor_color = 0xffa8a8a8;
    public int UserCell_statusOnlineColor_color = 0xff3b84c0;
    public int UserCell_nameTextView_color = 0xff212121;
    public int UserCell_checkBox_R = R.drawable.round_check2;




        //WallpaperCell
    public int WallpaperCell_selectionView_R = R.drawable.wall_selection;
    public int WallpaperCell_imageView2_img_R = R.drawable.ic_gallery_background;
    public int WallpaperCell_imageView2_bg_color_1 = 0x5a475866;
    public int WallpaperCell_imageView2_bg_color_2 = 0x5a000000;
    public int WallpaperCell_imageView_bg_color = 0xff000000;
    public int WallpaperCell_imageView_bg_color_2 = 0x5a475866;


/////////////////////////////////////////////////////////////////////////////////////////////////////


    //messenger


        //MessageObject
    public int MessageObject_textPaint_color = 0xff000000;
    public int MessageObject_textPaint_link_color = 0xff316f9f;
    public int MessageObject_textPaint_color_out_Color = 0xff000000;
    public int MessageObject_textPaint_color_in_Color = 0xff000000;

    public Typeface MessageObject_textPaint_typeface ;






    //////////////////////////////////////////////////////////////////////////////////////////////////


    //ActionBar


        //ActionBar
    public int ActionBar_subTitleTextView_color = 0xffd7e8f7;
    public int ActionBar_titleTextView_color = 0xffffffff;
    public int ActionBar_actionMode_bg_color = 0xffffffff;
    public int ActionBar_actionModeTop_bg_color = 0x99000000;
    public int ActionBar_backButtonImageView_R = R.drawable.bar_selector_mode;


        //ActionBarLayout
    public int ActionBarLayout_layerShadowDrawable_R = R.drawable.layer_shadow;
    public int ActionBarLayout_headerShadowDrawable_R = R.drawable.header_shadow;
    public int ActionBarLayout_scrimPaint_color_1 = 0x99000000;
    public int ActionBarLayout_scrimPaint_color_2 = 0xff000000;
    public int ActionBarLayout_fragmentView_bg_color = 0xffffffff;




        //ActionBarMenuItem
    public int ActionBarMenuItem_textView_color = 0xff212121;
    ///public int ActionBarMenuItem_textView_bg_R = R.drawable.list_selector;
    public int ActionBarMenuItem_searchField_hint_color = 0x88ffffff;
    public int ActionBarMenuItem_searchField_color = 0xffffffff;
    public int ActionBarMenuItem_mCursorDrawableRes_R = R.drawable.search_carret;
    public int ActionBarMenuItem_clearButton_R = R.drawable.ic_close_white;





        //ActionBarPopupWindow
    public int ActionBarPopupWindow_backgroundDrawable_R = R.drawable.popup_fixed;





        //BackDrawable
    public int BackDrawable_paint_color = 0xffffffff;






        //BaseFragment
    public int BaseFragment_actionBar_bg_color = 0xffff5500;
    public int BaseFragment_actionBar_R = R.drawable.bar_selector;




        //BottomSheet
    public int BottomSheet_backgroundDrawable_color = 0xff000000;
    ///public int BottomSheet_bg_R = R.drawable.list_selector;
    public int BottomSheet_textView_color_1 = 0xff757575;
    public int BottomSheet_textView_color_2 = 0xff212121;
    public int BottomSheet_window_R = R.drawable.transparent;
    public int BottomSheet_shadowDrawable_R = R.drawable.sheet_shadow;
    public int BottomSheet_ciclePaint_color = 0xffffffff;
    public int BottomSheet_titleView_color = 0xff757575;
    public int BottomSheet_lineView_bg_color = 0xffd2d2d2;




        //DrawerLayoutContainer;
    public int DrawerLayoutContainer_scrimPaint_color_1 = 0x99000000;
    public int DrawerLayoutContainer_scrimPaint_color_2 = 0xff000000;
    public int DrawerLayoutContainer_shadowLeft_R = R.drawable.menu_shadow;





        //MenuDrawable
    public int MenuDrawable_paint_color = 0xffffffff;












































































    //Adapters


        //ContactsAdapter
    public int ContactsAdapter_actionCell_invite_R = R.drawable.menu_invite;
    public int ContactsAdapter_actionCell_inviteg_R = R.drawable.menu_invite;
    public int ContactsAdapter_actionCell_newG_R = R.drawable.menu_newgroup;
    public int ContactsAdapter_actionCell_neschat_R = R.drawable.menu_secret;
    public int ContactsAdapter_actionCell_newchannel_R = R.drawable.menu_broadcast;




        //DialogsSearchAdapter
    ///public int DialogsSearchAdapter_view_bg_R = R.drawable.list_selector;




        //DrawerLayoutAdapter
    public int DrawerLayoutAdapter_actionCell_newg_R = R.drawable.menu_newgroup;
    public int DrawerLayoutAdapter_actionCell_newschat_R = R.drawable.menu_secret;
    public int DrawerLayoutAdapter_actionCell_newchannel_R = R.drawable.menu_broadcast;
    public int DrawerLayoutAdapter_actionCell_contacts_R = R.drawable.menu_contacts;
    public int DrawerLayoutAdapter_actionCell_invite_friends_R = R.drawable.menu_invite;
    public int DrawerLayoutAdapter_actionCell_settings_R = R.drawable.menu_settings;
    public int DrawerLayoutAdapter_actionCell_telegram_faqs_R = R.drawable.menu_help;





////////////////////////////////////////////////////////////////////////////////////////////////////


    //ui


        //activitys

        //DialogsActivity
    public int DialogsActivity_textView_color = 0xff959595;
    public int DialogsActivity_floatingButton_bg_R = R.drawable.floating_states;
    public Drawable DialogsActivity_floatingButton_bg;
    public int DialogsActivity_floatingButton_img_R = R.drawable.floating_pencil;
    public int DialogsActivity_listView_bg_color = Color.TRANSPARENT;



        //LaunchActivity

    public int LaunchActivity_window_bg_R = R.drawable.transparent;
    public int LaunchActivity_shadowTabletSide_bg_color = 0x40295274;
    public int LaunchActivity_shadowTablet_bg_color = 0x7F000000;
    public int LaunchActivity_layersActionBarLayout_bg_R = R.drawable.boxshadow;
    public int LaunchActivity_listView_bg_color = Color.WHITE;
    public int LaunchActivity_shadowTablet_bg_color_2 = 0x00000000;
    public int LaunchActivity_shadowTablet_bg_color_3 = 0x7F000000;



        //ChatActivity
    public int ChatActivity_contentView_bg_color = 0;



        //ChatActivityEnterView
    public int ChatActivityEnterView_textFieldContainer_color = 0xffffffff;
    public int ChatActivityEnterView__messageEditText_text_color = 0xff000000;
    public int ChatActivityEnterView__messageEditText_hint_color = 0xffb2b2b2;
    public int ChatActivityEnterView__sendButton_img_R = R.drawable.ic_send;
    public Bitmap sendBitmap;
    public int ChatActivityEnterView__bg_color = 0xffb2b2b2;















}
