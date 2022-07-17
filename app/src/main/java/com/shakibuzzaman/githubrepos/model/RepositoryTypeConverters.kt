package com.shakibuzzaman.githubrepos.model

import com.shakibuzzaman.githubrepos.base.BaseListTypeConverter
import com.shakibuzzaman.githubrepos.base.BaseTypeConverter

class OwnerTypeConverter : BaseTypeConverter<Owner>()
class LicenseTypeConverter : BaseTypeConverter<License>()
class TopicsTypeConverter : BaseListTypeConverter<String>()