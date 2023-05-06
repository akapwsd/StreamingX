// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: channelImform.proto

#ifndef PROTOBUF_channelImform_2eproto__INCLUDED
#define PROTOBUF_channelImform_2eproto__INCLUDED

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
#include "channelBase.pb.h"
// @@protoc_insertion_point(includes)

namespace uyujoy {
namespace com {
namespace api {
namespace channel {
namespace frontend {

// Internal implementation detail -- do not call these.
void protobuf_AddDesc_channelImform_2eproto();
void protobuf_InitDefaults_channelImform_2eproto();
void protobuf_AssignDesc_channelImform_2eproto();
void protobuf_ShutdownFile_channelImform_2eproto();

class channelStateChange;
class channelUserStateChange;

// ===================================================================

class channelStateChange : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:uyujoy.com.api.channel.frontend.channelStateChange) */ {
 public:
  channelStateChange();
  virtual ~channelStateChange();

  channelStateChange(const channelStateChange& from);

  inline channelStateChange& operator=(const channelStateChange& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const channelStateChange& default_instance();

  static const channelStateChange* internal_default_instance();

  void Swap(channelStateChange* other);

  // implements Message ----------------------------------------------

  inline channelStateChange* New() const { return New(NULL); }

  channelStateChange* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const channelStateChange& from);
  void MergeFrom(const channelStateChange& from);
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
  void InternalSwap(channelStateChange* other);
  void UnsafeMergeFrom(const channelStateChange& from);
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

  // optional .uyujoy.com.api.channel.frontend.channel ch = 1;
  bool has_ch() const;
  void clear_ch();
  static const int kChFieldNumber = 1;
  const ::uyujoy::com::api::channel::frontend::channel& ch() const;
  ::uyujoy::com::api::channel::frontend::channel* mutable_ch();
  ::uyujoy::com::api::channel::frontend::channel* release_ch();
  void set_allocated_ch(::uyujoy::com::api::channel::frontend::channel* ch);

  // optional uint32 reason = 2;
  void clear_reason();
  static const int kReasonFieldNumber = 2;
  ::google::protobuf::uint32 reason() const;
  void set_reason(::google::protobuf::uint32 value);

  // @@protoc_insertion_point(class_scope:uyujoy.com.api.channel.frontend.channelStateChange)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::uyujoy::com::api::channel::frontend::channel* ch_;
  ::google::protobuf::uint32 reason_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_channelImform_2eproto_impl();
  friend void  protobuf_AddDesc_channelImform_2eproto_impl();
  friend void protobuf_AssignDesc_channelImform_2eproto();
  friend void protobuf_ShutdownFile_channelImform_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<channelStateChange> channelStateChange_default_instance_;

// -------------------------------------------------------------------

class channelUserStateChange : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:uyujoy.com.api.channel.frontend.channelUserStateChange) */ {
 public:
  channelUserStateChange();
  virtual ~channelUserStateChange();

  channelUserStateChange(const channelUserStateChange& from);

  inline channelUserStateChange& operator=(const channelUserStateChange& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const channelUserStateChange& default_instance();

  static const channelUserStateChange* internal_default_instance();

  void Swap(channelUserStateChange* other);

  // implements Message ----------------------------------------------

  inline channelUserStateChange* New() const { return New(NULL); }

  channelUserStateChange* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const channelUserStateChange& from);
  void MergeFrom(const channelUserStateChange& from);
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
  void InternalSwap(channelUserStateChange* other);
  void UnsafeMergeFrom(const channelUserStateChange& from);
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

  // optional .uyujoy.com.api.channel.frontend.channelUser chu = 1;
  bool has_chu() const;
  void clear_chu();
  static const int kChuFieldNumber = 1;
  const ::uyujoy::com::api::channel::frontend::channelUser& chu() const;
  ::uyujoy::com::api::channel::frontend::channelUser* mutable_chu();
  ::uyujoy::com::api::channel::frontend::channelUser* release_chu();
  void set_allocated_chu(::uyujoy::com::api::channel::frontend::channelUser* chu);

  // optional uint32 reason = 2;
  void clear_reason();
  static const int kReasonFieldNumber = 2;
  ::google::protobuf::uint32 reason() const;
  void set_reason(::google::protobuf::uint32 value);

  // @@protoc_insertion_point(class_scope:uyujoy.com.api.channel.frontend.channelUserStateChange)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::uyujoy::com::api::channel::frontend::channelUser* chu_;
  ::google::protobuf::uint32 reason_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_channelImform_2eproto_impl();
  friend void  protobuf_AddDesc_channelImform_2eproto_impl();
  friend void protobuf_AssignDesc_channelImform_2eproto();
  friend void protobuf_ShutdownFile_channelImform_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<channelUserStateChange> channelUserStateChange_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// channelStateChange

// optional .uyujoy.com.api.channel.frontend.channel ch = 1;
inline bool channelStateChange::has_ch() const {
  return this != internal_default_instance() && ch_ != NULL;
}
inline void channelStateChange::clear_ch() {
  if (GetArenaNoVirtual() == NULL && ch_ != NULL) delete ch_;
  ch_ = NULL;
}
inline const ::uyujoy::com::api::channel::frontend::channel& channelStateChange::ch() const {
  // @@protoc_insertion_point(field_get:uyujoy.com.api.channel.frontend.channelStateChange.ch)
  return ch_ != NULL ? *ch_
                         : *::uyujoy::com::api::channel::frontend::channel::internal_default_instance();
}
inline ::uyujoy::com::api::channel::frontend::channel* channelStateChange::mutable_ch() {
  
  if (ch_ == NULL) {
    ch_ = new ::uyujoy::com::api::channel::frontend::channel;
  }
  // @@protoc_insertion_point(field_mutable:uyujoy.com.api.channel.frontend.channelStateChange.ch)
  return ch_;
}
inline ::uyujoy::com::api::channel::frontend::channel* channelStateChange::release_ch() {
  // @@protoc_insertion_point(field_release:uyujoy.com.api.channel.frontend.channelStateChange.ch)
  
  ::uyujoy::com::api::channel::frontend::channel* temp = ch_;
  ch_ = NULL;
  return temp;
}
inline void channelStateChange::set_allocated_ch(::uyujoy::com::api::channel::frontend::channel* ch) {
  delete ch_;
  ch_ = ch;
  if (ch) {
    
  } else {
    
  }
  // @@protoc_insertion_point(field_set_allocated:uyujoy.com.api.channel.frontend.channelStateChange.ch)
}

// optional uint32 reason = 2;
inline void channelStateChange::clear_reason() {
  reason_ = 0u;
}
inline ::google::protobuf::uint32 channelStateChange::reason() const {
  // @@protoc_insertion_point(field_get:uyujoy.com.api.channel.frontend.channelStateChange.reason)
  return reason_;
}
inline void channelStateChange::set_reason(::google::protobuf::uint32 value) {
  
  reason_ = value;
  // @@protoc_insertion_point(field_set:uyujoy.com.api.channel.frontend.channelStateChange.reason)
}

inline const channelStateChange* channelStateChange::internal_default_instance() {
  return &channelStateChange_default_instance_.get();
}
// -------------------------------------------------------------------

// channelUserStateChange

// optional .uyujoy.com.api.channel.frontend.channelUser chu = 1;
inline bool channelUserStateChange::has_chu() const {
  return this != internal_default_instance() && chu_ != NULL;
}
inline void channelUserStateChange::clear_chu() {
  if (GetArenaNoVirtual() == NULL && chu_ != NULL) delete chu_;
  chu_ = NULL;
}
inline const ::uyujoy::com::api::channel::frontend::channelUser& channelUserStateChange::chu() const {
  // @@protoc_insertion_point(field_get:uyujoy.com.api.channel.frontend.channelUserStateChange.chu)
  return chu_ != NULL ? *chu_
                         : *::uyujoy::com::api::channel::frontend::channelUser::internal_default_instance();
}
inline ::uyujoy::com::api::channel::frontend::channelUser* channelUserStateChange::mutable_chu() {
  
  if (chu_ == NULL) {
    chu_ = new ::uyujoy::com::api::channel::frontend::channelUser;
  }
  // @@protoc_insertion_point(field_mutable:uyujoy.com.api.channel.frontend.channelUserStateChange.chu)
  return chu_;
}
inline ::uyujoy::com::api::channel::frontend::channelUser* channelUserStateChange::release_chu() {
  // @@protoc_insertion_point(field_release:uyujoy.com.api.channel.frontend.channelUserStateChange.chu)
  
  ::uyujoy::com::api::channel::frontend::channelUser* temp = chu_;
  chu_ = NULL;
  return temp;
}
inline void channelUserStateChange::set_allocated_chu(::uyujoy::com::api::channel::frontend::channelUser* chu) {
  delete chu_;
  chu_ = chu;
  if (chu) {
    
  } else {
    
  }
  // @@protoc_insertion_point(field_set_allocated:uyujoy.com.api.channel.frontend.channelUserStateChange.chu)
}

// optional uint32 reason = 2;
inline void channelUserStateChange::clear_reason() {
  reason_ = 0u;
}
inline ::google::protobuf::uint32 channelUserStateChange::reason() const {
  // @@protoc_insertion_point(field_get:uyujoy.com.api.channel.frontend.channelUserStateChange.reason)
  return reason_;
}
inline void channelUserStateChange::set_reason(::google::protobuf::uint32 value) {
  
  reason_ = value;
  // @@protoc_insertion_point(field_set:uyujoy.com.api.channel.frontend.channelUserStateChange.reason)
}

inline const channelUserStateChange* channelUserStateChange::internal_default_instance() {
  return &channelUserStateChange_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS
// -------------------------------------------------------------------


// @@protoc_insertion_point(namespace_scope)

}  // namespace frontend
}  // namespace channel
}  // namespace api
}  // namespace com
}  // namespace uyujoy

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_channelImform_2eproto__INCLUDED