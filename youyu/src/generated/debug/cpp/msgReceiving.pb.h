// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: msgReceiving.proto

#ifndef PROTOBUF_msgReceiving_2eproto__INCLUDED
#define PROTOBUF_msgReceiving_2eproto__INCLUDED

#include <string>

#include <google/protobuf/stubs/common.h>

#if GOOGLE_PROTOBUF_VERSION < 3001000
#error This file was generated by a newer version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please update
#error your headers.
#endif
#if 3001000 < GOOGLE_PROTOBUF_MIN_PROTOC_VERSION
#error This file was generated by an older version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please
#error regenerate this file with a newer version of protoc.
#endif

#include <google/protobuf/arena.h>
#include <google/protobuf/arenastring.h>
#include <google/protobuf/generated_message_util.h>
#include <google/protobuf/metadata.h>
#include <google/protobuf/message.h>
#include <google/protobuf/repeated_field.h>
#include <google/protobuf/extension_set.h>
#include <google/protobuf/unknown_field_set.h>
#include "updatesBase.pb.h"
#include "msgBase.pb.h"
// @@protoc_insertion_point(includes)

namespace uyujoy {
namespace api {
namespace paasim {
namespace frontend {

// Internal implementation detail -- do not call these.
void protobuf_AddDesc_msgReceiving_2eproto();
void protobuf_InitDefaults_msgReceiving_2eproto();
void protobuf_AssignDesc_msgReceiving_2eproto();
void protobuf_ShutdownFile_msgReceiving_2eproto();

class shortMessage;
class updateNewMessages;

// ===================================================================

class shortMessage : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:uyujoy.api.paasim.frontend.shortMessage) */ {
 public:
  shortMessage();
  virtual ~shortMessage();

  shortMessage(const shortMessage& from);

  inline shortMessage& operator=(const shortMessage& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const shortMessage& default_instance();

  static const shortMessage* internal_default_instance();

  void Swap(shortMessage* other);

  // implements Message ----------------------------------------------

  inline shortMessage* New() const { return New(NULL); }

  shortMessage* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const shortMessage& from);
  void MergeFrom(const shortMessage& from);
  void Clear();
  bool IsInitialized() const;

  size_t ByteSizeLong() const;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input);
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const;
  ::google::protobuf::uint8* InternalSerializeWithCachedSizesToArray(
      bool deterministic, ::google::protobuf::uint8* output) const;
  ::google::protobuf::uint8* SerializeWithCachedSizesToArray(::google::protobuf::uint8* output) const {
    return InternalSerializeWithCachedSizesToArray(false, output);
  }
  int GetCachedSize() const { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const;
  void InternalSwap(shortMessage* other);
  void UnsafeMergeFrom(const shortMessage& from);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return _internal_metadata_.arena();
  }
  inline void* MaybeArenaPtr() const {
    return _internal_metadata_.raw_arena_ptr();
  }
  public:

  ::google::protobuf::Metadata GetMetadata() const;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // optional .uyujoy.api.paasim.frontend.paasMsgRecord msg = 1;
  bool has_msg() const;
  void clear_msg();
  static const int kMsgFieldNumber = 1;
  const ::uyujoy::api::paasim::frontend::paasMsgRecord& msg() const;
  ::uyujoy::api::paasim::frontend::paasMsgRecord* mutable_msg();
  ::uyujoy::api::paasim::frontend::paasMsgRecord* release_msg();
  void set_allocated_msg(::uyujoy::api::paasim::frontend::paasMsgRecord* msg);

  // optional int64 pts = 2;
  void clear_pts();
  static const int kPtsFieldNumber = 2;
  ::google::protobuf::int64 pts() const;
  void set_pts(::google::protobuf::int64 value);

  // optional uint32 pts_count = 3;
  void clear_pts_count();
  static const int kPtsCountFieldNumber = 3;
  ::google::protobuf::uint32 pts_count() const;
  void set_pts_count(::google::protobuf::uint32 value);

  // optional int64 date = 4;
  void clear_date();
  static const int kDateFieldNumber = 4;
  ::google::protobuf::int64 date() const;
  void set_date(::google::protobuf::int64 value);

  // optional int64 seq = 5;
  void clear_seq();
  static const int kSeqFieldNumber = 5;
  ::google::protobuf::int64 seq() const;
  void set_seq(::google::protobuf::int64 value);

  // @@protoc_insertion_point(class_scope:uyujoy.api.paasim.frontend.shortMessage)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::uyujoy::api::paasim::frontend::paasMsgRecord* msg_;
  ::google::protobuf::int64 pts_;
  ::google::protobuf::int64 date_;
  ::google::protobuf::int64 seq_;
  ::google::protobuf::uint32 pts_count_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_msgReceiving_2eproto_impl();
  friend void  protobuf_AddDesc_msgReceiving_2eproto_impl();
  friend void protobuf_AssignDesc_msgReceiving_2eproto();
  friend void protobuf_ShutdownFile_msgReceiving_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<shortMessage> shortMessage_default_instance_;

// -------------------------------------------------------------------

class updateNewMessages : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:uyujoy.api.paasim.frontend.updateNewMessages) */ {
 public:
  updateNewMessages();
  virtual ~updateNewMessages();

  updateNewMessages(const updateNewMessages& from);

  inline updateNewMessages& operator=(const updateNewMessages& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const updateNewMessages& default_instance();

  static const updateNewMessages* internal_default_instance();

  void Swap(updateNewMessages* other);

  // implements Message ----------------------------------------------

  inline updateNewMessages* New() const { return New(NULL); }

  updateNewMessages* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const updateNewMessages& from);
  void MergeFrom(const updateNewMessages& from);
  void Clear();
  bool IsInitialized() const;

  size_t ByteSizeLong() const;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input);
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const;
  ::google::protobuf::uint8* InternalSerializeWithCachedSizesToArray(
      bool deterministic, ::google::protobuf::uint8* output) const;
  ::google::protobuf::uint8* SerializeWithCachedSizesToArray(::google::protobuf::uint8* output) const {
    return InternalSerializeWithCachedSizesToArray(false, output);
  }
  int GetCachedSize() const { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const;
  void InternalSwap(updateNewMessages* other);
  void UnsafeMergeFrom(const updateNewMessages& from);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return _internal_metadata_.arena();
  }
  inline void* MaybeArenaPtr() const {
    return _internal_metadata_.raw_arena_ptr();
  }
  public:

  ::google::protobuf::Metadata GetMetadata() const;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // repeated .uyujoy.api.paasim.frontend.updateNewMessage msgs = 1;
  int msgs_size() const;
  void clear_msgs();
  static const int kMsgsFieldNumber = 1;
  const ::uyujoy::api::paasim::frontend::updateNewMessage& msgs(int index) const;
  ::uyujoy::api::paasim::frontend::updateNewMessage* mutable_msgs(int index);
  ::uyujoy::api::paasim::frontend::updateNewMessage* add_msgs();
  ::google::protobuf::RepeatedPtrField< ::uyujoy::api::paasim::frontend::updateNewMessage >*
      mutable_msgs();
  const ::google::protobuf::RepeatedPtrField< ::uyujoy::api::paasim::frontend::updateNewMessage >&
      msgs() const;

  // optional uint32 seq = 2;
  void clear_seq();
  static const int kSeqFieldNumber = 2;
  ::google::protobuf::uint32 seq() const;
  void set_seq(::google::protobuf::uint32 value);

  // @@protoc_insertion_point(class_scope:uyujoy.api.paasim.frontend.updateNewMessages)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::RepeatedPtrField< ::uyujoy::api::paasim::frontend::updateNewMessage > msgs_;
  ::google::protobuf::uint32 seq_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_msgReceiving_2eproto_impl();
  friend void  protobuf_AddDesc_msgReceiving_2eproto_impl();
  friend void protobuf_AssignDesc_msgReceiving_2eproto();
  friend void protobuf_ShutdownFile_msgReceiving_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<updateNewMessages> updateNewMessages_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// shortMessage

// optional .uyujoy.api.paasim.frontend.paasMsgRecord msg = 1;
inline bool shortMessage::has_msg() const {
  return this != internal_default_instance() && msg_ != NULL;
}
inline void shortMessage::clear_msg() {
  if (GetArenaNoVirtual() == NULL && msg_ != NULL) delete msg_;
  msg_ = NULL;
}
inline const ::uyujoy::api::paasim::frontend::paasMsgRecord& shortMessage::msg() const {
  // @@protoc_insertion_point(field_get:uyujoy.api.paasim.frontend.shortMessage.msg)
  return msg_ != NULL ? *msg_
                         : *::uyujoy::api::paasim::frontend::paasMsgRecord::internal_default_instance();
}
inline ::uyujoy::api::paasim::frontend::paasMsgRecord* shortMessage::mutable_msg() {
  
  if (msg_ == NULL) {
    msg_ = new ::uyujoy::api::paasim::frontend::paasMsgRecord;
  }
  // @@protoc_insertion_point(field_mutable:uyujoy.api.paasim.frontend.shortMessage.msg)
  return msg_;
}
inline ::uyujoy::api::paasim::frontend::paasMsgRecord* shortMessage::release_msg() {
  // @@protoc_insertion_point(field_release:uyujoy.api.paasim.frontend.shortMessage.msg)
  
  ::uyujoy::api::paasim::frontend::paasMsgRecord* temp = msg_;
  msg_ = NULL;
  return temp;
}
inline void shortMessage::set_allocated_msg(::uyujoy::api::paasim::frontend::paasMsgRecord* msg) {
  delete msg_;
  msg_ = msg;
  if (msg) {
    
  } else {
    
  }
  // @@protoc_insertion_point(field_set_allocated:uyujoy.api.paasim.frontend.shortMessage.msg)
}

// optional int64 pts = 2;
inline void shortMessage::clear_pts() {
  pts_ = GOOGLE_LONGLONG(0);
}
inline ::google::protobuf::int64 shortMessage::pts() const {
  // @@protoc_insertion_point(field_get:uyujoy.api.paasim.frontend.shortMessage.pts)
  return pts_;
}
inline void shortMessage::set_pts(::google::protobuf::int64 value) {
  
  pts_ = value;
  // @@protoc_insertion_point(field_set:uyujoy.api.paasim.frontend.shortMessage.pts)
}

// optional uint32 pts_count = 3;
inline void shortMessage::clear_pts_count() {
  pts_count_ = 0u;
}
inline ::google::protobuf::uint32 shortMessage::pts_count() const {
  // @@protoc_insertion_point(field_get:uyujoy.api.paasim.frontend.shortMessage.pts_count)
  return pts_count_;
}
inline void shortMessage::set_pts_count(::google::protobuf::uint32 value) {
  
  pts_count_ = value;
  // @@protoc_insertion_point(field_set:uyujoy.api.paasim.frontend.shortMessage.pts_count)
}

// optional int64 date = 4;
inline void shortMessage::clear_date() {
  date_ = GOOGLE_LONGLONG(0);
}
inline ::google::protobuf::int64 shortMessage::date() const {
  // @@protoc_insertion_point(field_get:uyujoy.api.paasim.frontend.shortMessage.date)
  return date_;
}
inline void shortMessage::set_date(::google::protobuf::int64 value) {
  
  date_ = value;
  // @@protoc_insertion_point(field_set:uyujoy.api.paasim.frontend.shortMessage.date)
}

// optional int64 seq = 5;
inline void shortMessage::clear_seq() {
  seq_ = GOOGLE_LONGLONG(0);
}
inline ::google::protobuf::int64 shortMessage::seq() const {
  // @@protoc_insertion_point(field_get:uyujoy.api.paasim.frontend.shortMessage.seq)
  return seq_;
}
inline void shortMessage::set_seq(::google::protobuf::int64 value) {
  
  seq_ = value;
  // @@protoc_insertion_point(field_set:uyujoy.api.paasim.frontend.shortMessage.seq)
}

inline const shortMessage* shortMessage::internal_default_instance() {
  return &shortMessage_default_instance_.get();
}
// -------------------------------------------------------------------

// updateNewMessages

// repeated .uyujoy.api.paasim.frontend.updateNewMessage msgs = 1;
inline int updateNewMessages::msgs_size() const {
  return msgs_.size();
}
inline void updateNewMessages::clear_msgs() {
  msgs_.Clear();
}
inline const ::uyujoy::api::paasim::frontend::updateNewMessage& updateNewMessages::msgs(int index) const {
  // @@protoc_insertion_point(field_get:uyujoy.api.paasim.frontend.updateNewMessages.msgs)
  return msgs_.Get(index);
}
inline ::uyujoy::api::paasim::frontend::updateNewMessage* updateNewMessages::mutable_msgs(int index) {
  // @@protoc_insertion_point(field_mutable:uyujoy.api.paasim.frontend.updateNewMessages.msgs)
  return msgs_.Mutable(index);
}
inline ::uyujoy::api::paasim::frontend::updateNewMessage* updateNewMessages::add_msgs() {
  // @@protoc_insertion_point(field_add:uyujoy.api.paasim.frontend.updateNewMessages.msgs)
  return msgs_.Add();
}
inline ::google::protobuf::RepeatedPtrField< ::uyujoy::api::paasim::frontend::updateNewMessage >*
updateNewMessages::mutable_msgs() {
  // @@protoc_insertion_point(field_mutable_list:uyujoy.api.paasim.frontend.updateNewMessages.msgs)
  return &msgs_;
}
inline const ::google::protobuf::RepeatedPtrField< ::uyujoy::api::paasim::frontend::updateNewMessage >&
updateNewMessages::msgs() const {
  // @@protoc_insertion_point(field_list:uyujoy.api.paasim.frontend.updateNewMessages.msgs)
  return msgs_;
}

// optional uint32 seq = 2;
inline void updateNewMessages::clear_seq() {
  seq_ = 0u;
}
inline ::google::protobuf::uint32 updateNewMessages::seq() const {
  // @@protoc_insertion_point(field_get:uyujoy.api.paasim.frontend.updateNewMessages.seq)
  return seq_;
}
inline void updateNewMessages::set_seq(::google::protobuf::uint32 value) {
  
  seq_ = value;
  // @@protoc_insertion_point(field_set:uyujoy.api.paasim.frontend.updateNewMessages.seq)
}

inline const updateNewMessages* updateNewMessages::internal_default_instance() {
  return &updateNewMessages_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS
// -------------------------------------------------------------------


// @@protoc_insertion_point(namespace_scope)

}  // namespace frontend
}  // namespace paasim
}  // namespace api
}  // namespace uyujoy

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_msgReceiving_2eproto__INCLUDED
